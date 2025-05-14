package com.controller;

import com.entity.Application;
import com.entity.Post;
import com.entity.AuthInfo;
import com.enums.Status;
import com.entity.UserInfo;
import com.exception.DeleteAppliedPostException;
import com.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    private final ApplicationService applicationService;
    private final UserInfoService userInfoService;

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
        postService.save(post, session);
        redirectAttributes.addFlashAttribute("success", "Đăng bài thành công");
        return "redirect:/posts/create";
    }

    @GetMapping("/{id}/edit")
    public String showUpdateForm(@PathVariable Long id, HttpSession session,
                                 RedirectAttributes redirectAttributes, Model model) {
        Post checkedPost = checkPostOwner(session, id);
        if (checkedPost == null) {
            redirectAttributes.addFlashAttribute("error", "Không thể chỉnh sửa bài đăng của người khác");
            return "redirect:/posts/{id}/edit";
        }
        model.addAttribute("checkedPost", checkedPost);
        return "post/edit";
    }

    private Post checkPostOwner(HttpSession session, Long postId) {
        Post post = postService.findById(postId);
        AuthInfo currentAuthInfo = (AuthInfo) session.getAttribute("authInfo");
        UserInfo ownerInfo = userInfoService.findById(post.getUserInfo().getId());
        if (currentAuthInfo.getUsername().equals(ownerInfo.getAuthInfo().getUsername())) {
            return post;
        }
        return null;
    }

    @PostMapping("/{id}/edit")
    public String update(@ModelAttribute Post post, RedirectAttributes redirectAttributes, HttpSession session) {
        postService.save(post, session);
        redirectAttributes.addFlashAttribute("success", "Sửa bài thành công");
        return "redirect:/posts/{id}/edit";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            postService.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Xóa bài thành công");
        } catch (DeleteAppliedPostException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/posts/my-post";
    }

    @GetMapping("/{id}/detail")
    public String showDetail(@PathVariable Long id, Model model) {
        Post post = postService.findById(id);
        model.addAttribute("post", post);
        List<Application> applicationList = applicationService.findByPost(post);
        model.addAttribute("applicationList", applicationList);
        return "post/detail";
    }

    @GetMapping("/{id}/approve")
    public String approvePost(@PathVariable Long id) {
        postService.approve(id);
        return "redirect:/posts/list";
    }

    @GetMapping("/{id}/reject")
    public String rejectPost(@PathVariable Long id) {
        postService.reject(id);
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
                Status postStatus = Status.valueOf(status);
                filteredPosts = postService.findByStatus(postStatus);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        model.addAttribute("posts", filteredPosts);
        return "post/list";
    }

    @GetMapping("/my-post")
    public String searchPosts(Model model, HttpSession session) {
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        model.addAttribute("posts", postService.findByUserInfo(userInfo));
        return "post/list";
    }

    @GetMapping("/{id}/apply")
    public String showApplyForm(@PathVariable Long id, Model model) {
        model.addAttribute("postId", id);
        return "post/apply";
    }

    @PostMapping("/{id}/apply")
    public String applyForPost(@PathVariable Long id,
                               @RequestParam("cvFile") MultipartFile cvFile,
                               HttpSession session,
                               RedirectAttributes redirectAttributes) {
        try {
            applicationService.save(id, session, cvFile);
            redirectAttributes.addFlashAttribute("success", "Gửi thành công");
            return "redirect:/posts/{id}/detail";
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("error", "Gửi thất bại");
            return "redirect:/posts/{id}/apply";
        }
    }
}
