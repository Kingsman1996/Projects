package com.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private UserInfo userInfo;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Post post;

    private String cvLink;

    private LocalDate appliedAt;
}
