package com.teamtaskmanager.dto;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.*;

@Getter
@Setter
public class RegisterRequest {
    @NotBlank(message = "{username.notBlank}")
    @Size(min = 4, max = 20, message = "{username.size}")
    private String username;

    @NotBlank(message = "{password.notBlank}")
    @Size(min = 4, message = "{password.size}")
    private String password;

    private String role;
}