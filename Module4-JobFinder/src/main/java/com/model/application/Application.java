package com.model.application;

import com.model.post.Post;
import com.model.user.User;
import lombok.Data;

import javax.persistence.*;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
@Data
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User candidate;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Post post;

    private String cvLink;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    private LocalDate appliedAt;
}
