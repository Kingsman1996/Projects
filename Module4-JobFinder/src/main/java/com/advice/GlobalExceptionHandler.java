package com.advice;

import com.exception.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AuthException.class)
    public String handleUsernameNotFoundException(AuthException ex,
                                                  RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", ex.getMessage());
        redirectAttributes.addFlashAttribute("loginForm", ex.getLoginForm());
        return "redirect:/auth/login";
    }

    @ExceptionHandler(DuplicateDataException.class)
    public String handleDuplicatePhoneNumberException(DuplicateDataException ex,
                                                      RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", ex.getMessage());
        redirectAttributes.addFlashAttribute("editUserInfoForm", ex.getEditUserInfoForm());
        return "redirect:/users/update";
    }

    @ExceptionHandler(UsernameExistException.class)
    public String handleUsernameExistException(UsernameExistException ex,
                                               RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", ex.getMessage());
        redirectAttributes.addFlashAttribute("registerForm", ex.getRegisterForm());
        return "redirect:/auth/register";
    }

    @ExceptionHandler(DeleteAppliedPostException.class)
    public String handleDeleteAppliedPostException(DeleteAppliedPostException ex,
                                                   RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", ex.getMessage());
        return "redirect:/posts/my-post";
    }
}
