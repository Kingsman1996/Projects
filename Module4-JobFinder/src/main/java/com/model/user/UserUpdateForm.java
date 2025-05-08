package com.model.user;

import lombok.Data;

@Data
public class UserUpdateForm {
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
}
