package com.teamtaskmanager.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class AccountInfoRequest {
    @NotBlank(message = "{name.notBlank}")
    private String name;

    @NotBlank(message = "{email.notBlank}")
    @Email(message = "{email.invalid}")
    private String email;
    
    @Pattern(regexp = "^0\\d{9,10}$", message = "{phone.invalid}")
    private String phone;
}
