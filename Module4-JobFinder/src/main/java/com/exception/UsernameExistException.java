package com.exception;

import com.dto.RegisterForm;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsernameExistException extends RuntimeException {
    private RegisterForm registerForm;
    public UsernameExistException(String message, RegisterForm registerForm) {
        super(message);
        this.registerForm = registerForm;
    }
}
