package com.controller;

import com.entity.post.PostStatus;
import com.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/candidates")
public class CandidateController {
    private final PostService postService;

    @GetMapping
    public String showHome(Model model) {
        model.addAttribute("postList", postService.findByStatus(PostStatus.APPROVED));
        return "candidate/home";
    }
}
