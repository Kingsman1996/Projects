package com.dto;

import com.enums.Role;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RegisterForm extends LoginForm {
    private Role role;

    @Override
    @NotBlank(message = "Tài khoản không được để trống")
    @Size(min = 4, max = 20, message = "Tài khoản từ 4 đến 20 ký tự")
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    @NotBlank(message = "Mật khẩu không được để trống")
    @Size(min = 4, max = 20, message = "Mật khẩu từ 4 đến 20 ký tự")
    public String getPassword() {
        return super.getPassword();
    }
}
