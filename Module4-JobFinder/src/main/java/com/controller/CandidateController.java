package com.controller;

import com.model.post.PostStatus;
import com.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/candidates")
public class CandidateController {
    private final PostService postService;

    @Autowired
    public CandidateController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/home")
    public String showHome(Model model) {
        model.addAttribute("postList", postService.findByStatus(PostStatus.APPROVED));
        return "candidate/home";
    }
}
