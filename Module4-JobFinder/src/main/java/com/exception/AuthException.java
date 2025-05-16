package com.exception;

import com.dto.LoginForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthException extends RuntimeException {
    private LoginForm loginForm;

    public AuthException(String message, LoginForm loginForm) {
        super(message);
        this.loginForm = loginForm;
    }
}
