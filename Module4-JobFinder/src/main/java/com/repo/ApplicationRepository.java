package com.repo;

import com.model.application.Application;
import com.model.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByPost(Post post);
}
