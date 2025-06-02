package com.teamtaskmanager.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class AccountInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;

    @JoinColumn(nullable = false)
    @OneToOne(fetch = FetchType.EAGER)
    private Account account;
}
