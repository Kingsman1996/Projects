package com.controller;

import com.enums.Status;
import com.enums.Role;
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
        model.addAttribute("userCount", authInfoService.countByRoleNot(Role.ADMIN));
        model.addAttribute("candidateCount", authInfoService.countByRole(Role.CANDIDATE));
        model.addAttribute("recruiterCount", authInfoService.countByRole(Role.RECRUITER));
        model.addAttribute("postCount", postService.countAll());
        model.addAttribute("pendingCount", postService.countByStatus(Status.PENDING));
        model.addAttribute("approvedCount", postService.countByStatus(Status.APPROVED));
        model.addAttribute("rejectedCount", postService.countByStatus(Status.REJECTED));
        return "admin/home";
    }
}
