package com.model.post;

import com.model.application.Application;
import com.model.user.User;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tiêu đề không được để trống")
    private String title;

    @NotBlank(message = "Mô tả không được để trống")
    private String description;

    @Enumerated(EnumType.STRING)
    private PostStatus status;

    private LocalDate postedDate;

    @ManyToOne
    private User recruiter;

    @OneToMany(mappedBy = "post")
    private List<Application> applications;
}
