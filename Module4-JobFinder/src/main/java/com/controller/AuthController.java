package com.controller;

import com.dto.EditPasswordForm;
import com.dto.LoginForm;
import com.dto.RegisterForm;
import com.entity.Auth;
import com.service.AuthService;
import com.service.UserInfoService;
import com.util.BindingResultConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final UserInfoService userInfoService;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("registerForm", new RegisterForm());
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute RegisterForm registerForm,
                           BindingResult result,
                           RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", BindingResultConverter.convertToListString(result));
        } else {
            authService.register(registerForm);
            redirectAttributes.addFlashAttribute("success", "Đăng ký thành công");
        }
        return "redirect:/auth/register";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "auth/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginForm loginForm,
                        HttpSession session) {
        Auth auth = authService.login(loginForm);
        session.setAttribute("auth", auth);
        session.setAttribute("userInfo", userInfoService.findByAuthInfo(auth));
        return authService.getHomeUrlByAuth(auth);
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/auth/login";
    }

    @GetMapping("/edit-password")
    public String showUpdateForm(Model model) {
        model.addAttribute("editPasswordForm", new EditPasswordForm());
        return "auth/edit-password";
    }

    @PostMapping("/edit-password")
    public String updatePassword(@Valid @ModelAttribute EditPasswordForm editPasswordForm,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes,
                                 @SessionAttribute("auth") Auth auth,
                                 HttpSession session) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", BindingResultConverter.convertToListString(bindingResult));
        } else {
            authService.editPassword(auth, editPasswordForm);
            session.setAttribute("auth", auth);
            redirectAttributes.addFlashAttribute("success", "Đổi mật khẩu thành công");
        }
        return "redirect:/auth/edit-password";
    }
}
