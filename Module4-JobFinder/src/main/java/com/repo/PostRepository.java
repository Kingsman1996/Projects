package com.repo;

import com.model.post.Post;
import com.model.post.PostStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByStatus(PostStatus status);

    int countByStatus(PostStatus status);
}
