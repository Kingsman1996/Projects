package com.controller;

import com.model.user.User;
import com.model.user.UserRole;
import com.model.user.UserUpdateForm;
import com.service.UserInfoService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;


@Controller
@SessionAttributes({"currentUser", "currentUserInfo"})
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserInfoService userInfoService;

    @Autowired
    public UserController(UserService userService,
                          UserInfoService userInfoService) {
        this.userService = userService;
        this.userInfoService = userInfoService;
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("newUser", new User());
        return "user/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute User user,
                           BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "redirect:/users/register";
        }
        try {
            userService.register(user);
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/users/register";
        }
        redirectAttributes.addFlashAttribute("success", "Đăng ký thành công");
        return "redirect:/users/register";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "user/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model) {
        if (!userService.checkLogin(username, password)) {
            model.addAttribute("message", "Tên đăng nhập hoặc mật khẩu sai.");
            return "user/login";
        }
        User user = userService.findByUsername(username);
        model.addAttribute("currentUser", user);
        model.addAttribute("currentUserInfo", userInfoService.findByUser(user));
        switch (user.getRole()) {
            case ADMIN:
                return "redirect:/app/admin/";
            case CANDIDATE:
                return "redirect:/candidates/home";
            case RECRUITER:
                return "redirect:/recruiters/home";
        }
        return "redirect:/users/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/users/login";
    }

    @GetMapping("/update")
    public String showUpdateForm(Model model) {
        model.addAttribute("userUpdateForm", new UserUpdateForm());
        return "user/update";
    }

    @GetMapping("/profile")
    public String showProfile(HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        if (user == null) {
            return "redirect:/users/login";
        }
        return "user/profile";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute UserUpdateForm updatedForm,
                             HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("currentUser");
        String username = currentUser.getUsername();
        userService.update(username, updatedForm);
        currentUser = userService.findByUsername(username);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("currentUserInfo", userInfoService.findByUser(currentUser));
        return "redirect:/users/profile";
    }

    @GetMapping("/list")
    public String listUsersByRole(@RequestParam("role") String userRole, Model model) {
        UserRole userRoleEnum = UserRole.valueOf(userRole);
        List<User> users = userService.findByRole(userRoleEnum);
        model.addAttribute("users", users);
        return "user/list";
    }
}