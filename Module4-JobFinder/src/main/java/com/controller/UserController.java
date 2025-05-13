package com.controller;

import com.entity.user.AuthInfo;
import com.entity.user.UserRole;
import com.dto.UserUpdateForm;
import com.service.UserInfoService;
import com.service.AuthInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;


@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final AuthInfoService authInfoService;
    private final UserInfoService userInfoService;

    @GetMapping("/update")
    public String showUpdateForm(Model model) {
        UserUpdateForm userUpdateForm = new UserUpdateForm();
        model.addAttribute("userUpdateForm", userUpdateForm);
        return "user/update";
    }

    @GetMapping("/profile")
    public String showProfile() {
        return "user/profile";
    }

    @PostMapping("/update")
    public String updateUser(@Valid @ModelAttribute UserUpdateForm updatedForm, BindingResult bindingResult,
                             Model model, RedirectAttributes redirectAttributes, HttpSession session) {
        System.out.println(session.getAttribute("authInfo"));
        System.out.println(session.getAttribute("userInfo"));
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors",
                    Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
            return "redirect:/users/update";
        }
        AuthInfo currentAuthInfo = (AuthInfo) session.getAttribute("authInfo");
        updatedForm.setUsername(currentAuthInfo.getUsername());
        authInfoService.changePassword(updatedForm);
        userInfoService.update(updatedForm);
        AuthInfo newAuthInfo = authInfoService.findByUsername(updatedForm.getUsername());

        session.setAttribute("authInfo", newAuthInfo);
        session.setAttribute("userInfo", userInfoService.findByAuthInfo(newAuthInfo));

        model.addAttribute("authInfo", newAuthInfo);
        model.addAttribute("userInfo", userInfoService.findByAuthInfo(newAuthInfo));
        return "redirect:/users/profile";
    }

    @GetMapping("/list")
    public String listUsersByRole(@RequestParam("role") String userRole, Model model) {
        UserRole userRoleEnum = UserRole.valueOf(userRole);
        List<AuthInfo> authInfoList = authInfoService.findByRole(userRoleEnum);
        model.addAttribute("authInfoList", authInfoList);
        return "user/list";
    }
}