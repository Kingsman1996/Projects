package com.repo;

import com.entity.Post;
import com.enums.Status;
import com.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByStatus(Status status);

    List<Post> findByUserInfo(UserInfo userInfo);

    long countByStatus(Status status);
}
