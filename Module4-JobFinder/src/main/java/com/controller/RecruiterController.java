package com.controller;

import com.model.user.User;
import com.model.user.UserInfo;
import com.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/recruiters")
public class RecruiterController {
    private final UserInfoService userInfoService;

    @Autowired
    public RecruiterController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @GetMapping("/home")
    public String showHome(Model model, HttpSession session) {
        UserInfo userInfo = (UserInfo) session.getAttribute("currentUserInfo");
        String firstName = userInfo.getFirstName();
        String lastName = userInfo.getLastName();
        model.addAttribute("fullName", firstName+" "+lastName);
        return "recruiter/home";
    }
}
