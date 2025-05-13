package com.entity.post;

import com.entity.user.UserInfo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private PostStatus status;

    private LocalDate postedDate;

    @ManyToOne
    @JoinColumn(nullable = false)
    private UserInfo userInfo;
}
