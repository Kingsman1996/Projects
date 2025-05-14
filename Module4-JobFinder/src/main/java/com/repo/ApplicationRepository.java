package com.repo;

import com.entity.Application;
import com.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByPost(Post post);
    boolean existsByPost(Post post);
}
