package com.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserUpdateForm {
    private String username;

    @NotBlank(message = "Mật khẩu không được để trống")
    @Size(min = 3, max = 20, message = "Mật khẩu từ 3 đến 20 ký tự")
    private String password;

    private String firstName;
    private String lastName;

    @Email
    @NotBlank(message = "Email không được để trống")
    private String email;

    @NotBlank(message = "Số điện thoại không được để trống")
    @Size(min = 10, max = 11, message = "Số điện thoại từ 10 hoặc 11 ký tự")
    private String phone;

    private String address;
}
