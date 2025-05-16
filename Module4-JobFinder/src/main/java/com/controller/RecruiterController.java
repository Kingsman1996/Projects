package com.controller;

import com.entity.UserInfo;
import com.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/recruiters")
public class RecruiterController {
    private final UserInfoService userInfoService;

    @GetMapping
    public String showHome(Model model,
                           @SessionAttribute ("userInfo") UserInfo userInfo){
        String firstName = userInfo.getFirstName();
        String lastName = userInfo.getLastName();
        model.addAttribute("fullName", firstName + " " + lastName);
        return "recruiter/home";
    }
}
