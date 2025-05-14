package com.controller;

import com.dto.EditPasswordForm;
import com.dto.LoginForm;
import com.dto.RegisterForm;
import com.entity.AuthInfo;
import com.exception.InvalidPasswordException;
import com.exception.UsernameExistException;
import com.exception.UsernameNotFoundException;
import com.service.AuthInfoService;
import com.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@SessionAttributes({"authInfo", "userInfo"})
@RequestMapping("/auth")
public class AuthController {
    private final AuthInfoService authInfoService;
    private final UserInfoService userInfoService;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("registerForm", new RegisterForm());
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute RegisterForm registerForm,
                           BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("error",
                    Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
            return "redirect:/auth/register";
        }
        try {
            authInfoService.register(registerForm);
        } catch (UsernameExistException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/auth/register";
        }
        redirectAttributes.addFlashAttribute("success", "Đăng ký thành công");
        return "redirect:/auth/register";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        LoginForm loginForm = new LoginForm();
        model.addAttribute("loginForm", loginForm);
        return "auth/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginForm loginForm,
                        Model model, RedirectAttributes redirectAttributes) {
        try {
            AuthInfo authInfo = authInfoService.login(loginForm);
            model.addAttribute("authInfo", authInfo);
            model.addAttribute("userInfo", userInfoService.findByAuthInfo(authInfo));
            return getHomeUrlByAuthInfo(authInfo);
        } catch (UsernameNotFoundException | InvalidPasswordException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/auth/login";
        }
    }

    private String getHomeUrlByAuthInfo(AuthInfo authInfo) {
        String url = "";
        switch (authInfo.getRole()) {
            case ADMIN:
                url = "redirect:/admin";
                break;
            case CANDIDATE:
                url = "redirect:/candidates";
                break;
            case RECRUITER:
                url = "redirect:/recruiters";
                break;
            default:
                url = "redirect:/auth/login";
                break;
        }
        return url;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/auth/login";
    }

    @GetMapping("/update")
    public String showUpdateForm(Model model) {
        EditPasswordForm editPasswordForm = new EditPasswordForm();
        model.addAttribute("editPasswordForm", editPasswordForm);
        return "auth/update";
    }

    @PostMapping("/update")
    public String updatePassword(@Valid @ModelAttribute EditPasswordForm editPasswordForm,
                                 BindingResult bindingResult, RedirectAttributes redirectAttributes,
                                 HttpSession session) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("error",
                    Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
            return "redirect:/auth/update";
        }
        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
        session.setAttribute("authInfo", authInfoService.editPassword(authInfo, editPasswordForm));
        redirectAttributes.addFlashAttribute("success", "Đổi mật khẩu thành công");
        return "redirect:/auth/update";
    }
}
