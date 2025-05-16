package com.controller;

import com.dto.EditUserInfoForm;
import com.entity.Auth;
import com.enums.Role;
import com.entity.UserInfo;
import com.service.UserInfoService;
import com.service.AuthService;
import com.util.BindingResultConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final AuthService authService;
    private final UserInfoService userInfoService;

    @GetMapping("/profile")
    public String showProfile() {
        return "user/profile";
    }

    @GetMapping("/update")
    public String showUpdateForm(Model model, @SessionAttribute("userInfo") UserInfo currentUserInfo) {
        if (!model.containsAttribute("editUserInfoForm")) {
            EditUserInfoForm editUserInfoForm = new EditUserInfoForm();
            editUserInfoForm.setFirstName(currentUserInfo.getFirstName());
            editUserInfoForm.setLastName(currentUserInfo.getLastName());
            editUserInfoForm.setEmail(currentUserInfo.getEmail());
            editUserInfoForm.setPhone(currentUserInfo.getPhone());
            editUserInfoForm.setAddress(currentUserInfo.getAddress());
            model.addAttribute("editUserInfoForm", editUserInfoForm);
        }
        return "user/update";
    }

    @PostMapping("/update")
    public String updateUser(@Valid @ModelAttribute EditUserInfoForm editUserInfoForm,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             @SessionAttribute("userInfo") UserInfo currentUserInfo,
                             Model model, HttpSession session) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", BindingResultConverter.convertToListString(bindingResult));
            model.addAttribute("editUserInfoForm", editUserInfoForm);
            return "user/update";
        }
        session.setAttribute("userInfo", userInfoService.update(currentUserInfo, editUserInfoForm));
        redirectAttributes.addFlashAttribute("success", "Cập nhật thông tin thành công");
        return "redirect:/users/profile";
    }

    @GetMapping("/list")
    public String listUsersByRole(@RequestParam("role") String userRole, Model model) {
        List<Auth> authList;
        if (userRole == null || userRole.isEmpty()) {
            authList = authService.findByRoleNot(Role.ADMIN);
        } else {
            Role roleEnum = Role.valueOf(userRole);
            authList = authService.findByRole(roleEnum);
        }
        model.addAttribute("authList", authList);
        return "user/list";
    }
}