package com.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class EditPasswordForm {
    @NotBlank(message = "Mật khẩu không được để trống")
    @Size(min = 4, max = 20, message = "Mật khẩu phải từ 4 đến 20 ký tự")
    private String newPassword;
}
