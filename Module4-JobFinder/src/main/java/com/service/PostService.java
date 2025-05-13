package com.service;

import com.entity.post.Post;
import com.entity.post.PostStatus;
import com.repo.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor()
public class PostService {
    private final PostRepository postRepository;

    public void save(Post post) {
        post.setStatus(PostStatus.PENDING);
        post.setPostedDate(LocalDate.now());
        postRepository.save(post);
    }

    public Post findById(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        return optionalPost.orElse(null);
    }

    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }

    public List<Post> findByStatus(PostStatus status) {
        return postRepository.findByStatus(status);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public long countAll() {
        return postRepository.count();
    }

    public long countByStatus(PostStatus status) {
        return postRepository.countByStatus(status);
    }
}