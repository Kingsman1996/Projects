package com.controller;

import com.entity.post.PostStatus;
import com.entity.user.UserRole;
import com.service.PostService;
import com.service.AuthInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final PostService postService;
    private final AuthInfoService authInfoService;

    @GetMapping
    public String showHome(Model model) {
        model.addAttribute("userCount", authInfoService.count() - 1);
        model.addAttribute("candidateCount", authInfoService.countByRole(UserRole.CANDIDATE));
        model.addAttribute("recruiterCount", authInfoService.countByRole(UserRole.RECRUITER));
        model.addAttribute("postCount", postService.countAll());
        model.addAttribute("pendingCount", postService.countByStatus(PostStatus.PENDING));
        model.addAttribute("approvedCount", postService.countByStatus(PostStatus.APPROVED));
        model.addAttribute("rejectedCount", postService.countByStatus(PostStatus.REJECTED));
        return "admin/home";
    }
}
