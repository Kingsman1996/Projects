package com.repo;

import com.entity.post.Post;
import com.entity.post.PostStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByStatus(PostStatus status);

    long countByStatus(PostStatus status);
}
