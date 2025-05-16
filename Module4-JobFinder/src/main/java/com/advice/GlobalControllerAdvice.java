package com.advice;

import com.entity.Auth;
import com.entity.UserInfo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {
    @ModelAttribute("userInfo")
    public UserInfo addUserInfoToModel(@SessionAttribute(name = "userInfo", required = false) UserInfo userInfo) {
        return userInfo;
    }
    @ModelAttribute("auth")
    public Auth addAuthToModel(@SessionAttribute(name = "auth", required = false) Auth auth) {
        return auth;
    }
}
