package com.controller;

import com.entity.application.Application;
import com.entity.post.Post;
import com.entity.post.PostStatus;
import com.entity.user.AuthInfo;
import com.entity.user.UserInfo;
import com.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
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
        UserInfo recruiterInfo = (UserInfo) session.getAttribute("userInfo");
        post.setUserInfo(recruiterInfo);
        postService.save(post);
        redirectAttributes.addFlashAttribute("success", "Đăng bài thành công");
        return "redirect:/posts/create";
    }

    @GetMapping("/{id}/edit")
    public String showUpdateForm(@PathVariable Long id, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        Post existingPost = postService.findById(id);
        AuthInfo currentAuthInfo = (AuthInfo) session.getAttribute("authInfo");
        UserInfo ownerInfo = userInfoService.findById(existingPost.getUserInfo().getId());
        if (currentAuthInfo.getUsername().equals(ownerInfo.getAuthInfo().getUsername())) {
            model.addAttribute("post", existingPost);
            return "post/edit";
        }
        redirectAttributes.addFlashAttribute("error", "Không thể chỉnh sửa bài đăng của người khác");
        return "redirect:/posts/{id}/edit";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute Post post, RedirectAttributes redirectAttributes) {
        postService.save(post);
        redirectAttributes.addFlashAttribute("success", "Sửa bài thành công");
        return "redirect:/posts/{id}/edit";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes, HttpSession session) {
        Post existingPost = postService.findById(id);
        AuthInfo currentAuthInfo = (AuthInfo) session.getAttribute("authInfo");
        UserInfo ownerInfo = userInfoService.findById(existingPost.getUserInfo().getId());
        if (currentAuthInfo.getUsername().equals(ownerInfo.getAuthInfo().getUsername())) {
            postService.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Xóa bài thành công");
            return "redirect:/recruiters";
        }
        redirectAttributes.addFlashAttribute("error", "Không thể xóa bài của người khác");
        return "redirect:/posts/{id}/delete";
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
        model.addAttribute("postId", id);
        return "post/apply";
    }

    @PostMapping("/{postId}/apply")
    public String applyForPost(@PathVariable Long postId,
                               @RequestParam("cvFile") MultipartFile cvFile,
                               HttpSession session,
                               RedirectAttributes redirectAttributes) {
        UserInfo candidateInfo = (UserInfo) session.getAttribute("userInfo");
        applicationService.save(postId, candidateInfo, cvFile);
        redirectAttributes.addFlashAttribute("success", "Gửi thành công");
        return "redirect:/posts/{id}/detail";
    }
}
