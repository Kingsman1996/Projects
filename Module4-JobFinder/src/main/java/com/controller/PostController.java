package com.controller;

import com.entity.Application;
import com.entity.Post;
import com.enums.Status;
import com.entity.UserInfo;
import com.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    private final ApplicationService applicationService;

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("post", new Post());
        return "post/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Post post,
                         @SessionAttribute("userInfo") UserInfo recruiterInfo,
                         RedirectAttributes redirectAttributes) {
        post.setUserInfo(recruiterInfo);
        postService.save(post);
        redirectAttributes.addFlashAttribute("success", "Đăng bài thành công");
        return "redirect:/posts/create";
    }

    @GetMapping("/{id}/edit")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Post post = postService.findById(id);
        model.addAttribute("post", post);
        return "post/edit";
    }

    @PostMapping("/{id}/edit")
    public String update(@ModelAttribute Post post,
                         RedirectAttributes redirectAttributes,
                         @SessionAttribute ("userInfo") UserInfo userInfo) {
        post.setUserInfo(userInfo);
        postService.save(post);
        redirectAttributes.addFlashAttribute("success", "Sửa bài thành công");
        return "redirect:/posts/{id}/edit";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        postService.deleteById(id);
        redirectAttributes.addFlashAttribute("success", "Xóa bài thành công");
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
        List<Post> filteredPosts;
        if (status == null || status.isEmpty()) {
            filteredPosts = postService.findAll();
        } else {
            Status postStatus = Status.valueOf(status);
            filteredPosts = postService.findByStatus(postStatus);
        }
        model.addAttribute("posts", filteredPosts);
        return "post/list";
    }

    @GetMapping("/my-post")
    public String searchPosts(Model model,
                              @SessionAttribute("userInfo") UserInfo userInfo) {
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
                               @SessionAttribute("userInfo") UserInfo userInfo,
                               RedirectAttributes redirectAttributes) {
        applicationService.save(id, userInfo, cvFile);
        redirectAttributes.addFlashAttribute("success", "Gửi thành công");
        return "redirect:/posts/{id}/apply";
    }
}
