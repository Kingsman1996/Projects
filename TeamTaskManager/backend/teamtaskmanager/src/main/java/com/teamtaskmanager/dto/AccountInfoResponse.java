package com.teamtaskmanager.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AccountInfoResponse {
    private Long id;
    private String username;
    private String name;
    private String email;
    private String phone;
}
