package com.controller;

import com.model.application.Application;
import com.model.post.Post;
import com.model.post.PostStatus;
import com.model.user.UserInfo;
import com.model.user.User;
import com.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    private final ApplicationService applicationService;
    private final UserInfoService userInfoService;
    private final UserService userService;

    @Autowired
    public PostController(PostService postService,
                          ApplicationService applicationService,
                          UserInfoService userInfoService,
                          UserService userService) {
        this.postService = postService;
        this.applicationService = applicationService;
        this.userInfoService = userInfoService;
        this.userService = userService;
    }

    @ModelAttribute("userInfo")
    public UserInfo getUserInfo(HttpSession session) {
        User user = (User) session.getAttribute("user");
        return userInfoService.findByUser(user);
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        Post post = new Post();
        model.addAttribute("post", post);
        return "post/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Post post,
                         RedirectAttributes redirectAttributes,
                         HttpSession session) {
        User recruiter = (User) session.getAttribute("currentUser");
        post.setRecruiter(recruiter);
        postService.save(post);
        redirectAttributes.addFlashAttribute("success", "Đăng bài thành công");
        return "redirect:/posts/create";
    }

    @GetMapping("/{id}/edit")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Post existingPost = postService.findById(id);
        model.addAttribute("post", existingPost);
        return "post/edit";
    }

    @PostMapping("/{id}/edit")
    public String update(@ModelAttribute Post post, HttpSession session, RedirectAttributes redirectAttributes) {
        postService.save(post);
        redirectAttributes.addFlashAttribute("success", "Sửa bài thành công");
        return "redirect:/posts/{id}/edit";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        postService.deleteById(id);
        redirectAttributes.addFlashAttribute("success", "Xóa bài thành công");
        return "redirect:/recruiters/home";
    }

    @GetMapping("/{id}/detail")
    public String showDetail(@PathVariable Long id, Model model) {
        Post post = postService.findById(id);
        post.setApplications(applicationService.findByPost(post));
        model.addAttribute("post", post);
        User recruiter = userService.findById(post.getRecruiter().getId());
        UserInfo recruiterInfo = userInfoService.findByUser(recruiter);
        model.addAttribute("recruiterInfo", recruiterInfo);
        Map<LocalDate, UserInfo> applicationDetails = new HashMap<>();
        for (Application application : post.getApplications()) {
            applicationDetails.put(application.getAppliedAt(), userInfoService.findByApplicationId(application.getId()));
        }
        model.addAttribute("applicationDetails", applicationDetails);
        return "post/detail";
    }

    @GetMapping("/{id}/approve")
    public String approvePost(@PathVariable Long id) {
        Post foundPost = postService.findById(id);
        foundPost.setStatus(PostStatus.APPROVED);
        postService.save(foundPost);
        return "redirect:/posts/list";
    }

    @GetMapping("/{id}/reject")
    public String rejectPost(@PathVariable Long id) {
        Post foundPost = postService.findById(id);
        foundPost.setStatus(PostStatus.REJECTED);
        postService.save(foundPost);
        return "redirect:/posts/list";
    }

    @GetMapping("/list")
    public String searchPosts(@RequestParam(required = false) String status,
                              Model model) {
        List<Post> filteredPosts = new ArrayList<>();
        if (status == null || status.isEmpty()) {
            filteredPosts = postService.findAll();
        } else {
            try {
                PostStatus postStatus = PostStatus.valueOf(status);
                filteredPosts = postService.findByStatus(postStatus);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        model.addAttribute("posts", filteredPosts);
        return "post/list";
    }

    @GetMapping("/{id}/apply")
    public String showApplyForm(@PathVariable Long id, Model model) {
        model.addAttribute("userInfo", new UserInfo());
        model.addAttribute("postId", id);
        return "post/apply";
    }


    @PostMapping("/{id}/apply")
    public String applyForPost(@PathVariable Long id,
                               @RequestParam("cvFile") MultipartFile cvFile,
                               HttpSession session,
                               RedirectAttributes redirectAttributes) {
        User candidate = (User) session.getAttribute("currentUser");
        postService.applyForPost(id, candidate, cvFile);
        redirectAttributes.addFlashAttribute("success", "Gửi thành công");
        return "redirect:/posts/{id}/detail";
    }
}
