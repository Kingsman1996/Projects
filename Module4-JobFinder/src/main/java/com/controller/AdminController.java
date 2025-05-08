package com.controller;

import com.model.post.PostStatus;
import com.model.user.UserRole;
import com.service.PostService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app/admin")
public class AdminController {
    private final PostService postService;
    private final UserService userService;

    @Autowired
    public AdminController(PostService postService,
                           UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping
    public String showHome(Model model) {
        model.addAttribute("userCount", userService.count());
        model.addAttribute("candidateCount", userService.countByRole(UserRole.CANDIDATE));
        model.addAttribute("recruiterCount", userService.countByRole(UserRole.RECRUITER));
        model.addAttribute("postCount", postService.countAll());
        model.addAttribute("pendingCount", postService.countByStatus(PostStatus.PENDING));
        model.addAttribute("approvedCount", postService.countByStatus(PostStatus.APPROVED));
        model.addAttribute("rejectedCount", postService.countByStatus(PostStatus.REJECTED));
        return "admin/home";
    }
}
