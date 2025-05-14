package com.controller;

import com.dto.EditUserInfoForm;
import com.entity.AuthInfo;
import com.enums.Role;
import com.entity.UserInfo;
import com.exception.DuplicateEmailExeption;
import com.exception.DuplicatePhoneException;
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
        EditUserInfoForm editUserInfoForm = new EditUserInfoForm();
        model.addAttribute("editUserInfoForm", editUserInfoForm);
        return "user/update";
    }

    @GetMapping("/profile")
    public String showProfile() {
        return "user/profile";
    }

    @PostMapping("/update")
    public String updateUser(@Valid @ModelAttribute EditUserInfoForm editUserInfoForm,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             HttpSession session, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("editUserInfoForm", editUserInfoForm);
            model.addAttribute("error", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
            return "user/update";
        }
        UserInfo currentUserInfo = (UserInfo) session.getAttribute("userInfo");
        try {
            session.setAttribute("userInfo", userInfoService.update(currentUserInfo, editUserInfoForm));
            redirectAttributes.addFlashAttribute("success", "Cập nhật thông tin thành công");
            return "redirect:/users/profile";
        } catch (DuplicateEmailExeption | DuplicatePhoneException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("editUserInfoForm", editUserInfoForm);
            return "user/update";
        }
    }

    @GetMapping("/list")
    public String listUsersByRole(@RequestParam("role") String userRole, Model model) {
        List<AuthInfo> authInfoList;
        if (userRole == null || userRole.isEmpty()) {
            authInfoList = authInfoService.findByRoleNot(Role.ADMIN);
        } else {
            Role roleEnum = Role.valueOf(userRole);
            authInfoList = authInfoService.findByRole(roleEnum);
        }
        model.addAttribute("authInfoList", authInfoList);
        return "user/list";
    }
}