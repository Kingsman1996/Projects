package com.controller;

import com.dto.LoginRequest;
import com.dto.RegisterRequest;
import com.entity.user.AuthInfo;
import com.exception.UsernameExistException;
import com.service.AuthInfoService;
import com.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@SessionAttributes({"authInfo", "userInfo"})
@RequestMapping("/auth")
public class AuthController {
    private final AuthInfoService authInfoService;
    private final UserInfoService userInfoService;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("registerRequest", new RegisterRequest());
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute RegisterRequest registerRequest,
                           BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            List<String> errorsMessages = new ArrayList<>();
            for (ObjectError error : errors) {
                errorsMessages.add(error.getDefaultMessage());
            }
            redirectAttributes.addFlashAttribute("errors", errorsMessages);
            return "redirect:/auth/register";
        }
        try {
            authInfoService.register(registerRequest);
        } catch (UsernameExistException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/auth/register";
        }
        redirectAttributes.addFlashAttribute("success", "Đăng ký thành công");
        return "redirect:/auth/register";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        LoginRequest loginRequest = new LoginRequest();
        model.addAttribute("loginRequest", loginRequest);
        return "auth/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginRequest loginRequest, Model model, RedirectAttributes redirectAttributes) {
        if (!authInfoService.checkLogin(loginRequest)) {
            redirectAttributes.addFlashAttribute("message", "Tên đăng nhập hoặc mật khẩu sai.");
            return "redirect:/auth/login";
        }
        AuthInfo authInfo = authInfoService.findByUsername(loginRequest.getUsername());
        model.addAttribute("authInfo", authInfo);
        model.addAttribute("userInfo", userInfoService.findByAuthInfo(authInfo));
        switch (authInfo.getRole()) {
            case ADMIN:
                return "redirect:/admin";
            case CANDIDATE:
                return "redirect:/candidates";
            case RECRUITER:
                return "redirect:/recruiters";
            default:
                return "redirect:/auth/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/auth/login";
    }
}
