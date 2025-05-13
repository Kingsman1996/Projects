package com.dto;

import com.entity.user.UserRole;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RegisterRequest {
    @NotBlank
    @Size(min = 4, max = 20, message = "Tài khoản từ 4 đến 20 ký tự")
    private String username;
    @NotBlank
    @Size(min = 4, max = 20, message = "Mật khẩu từ 4 đến 20 ký tự")
    private String password;

    private UserRole role;
}
