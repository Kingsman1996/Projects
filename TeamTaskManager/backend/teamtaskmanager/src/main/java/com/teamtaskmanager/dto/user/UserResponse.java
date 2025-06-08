package com.teamtaskmanager.dto.user;


import com.teamtaskmanager.entity.Authority;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class UserResponse {
    private Long id;
    private String username;
    private String fullName;
    private String email;
    private String phone;
    private Set<Authority> authorities;
}
