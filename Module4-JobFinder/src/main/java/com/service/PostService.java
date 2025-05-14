package com.service;

import com.entity.Post;
import com.enums.Status;
import com.entity.UserInfo;
import com.exception.DeleteAppliedPostException;
import com.repo.ApplicationRepository;
import com.repo.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor()
public class PostService {
    private final PostRepository postRepository;
    private final ApplicationRepository applicationRepository;

    public void save(Post post, HttpSession session) {
        UserInfo recruiterInfo = (UserInfo) session.getAttribute("userInfo");
        post.setUserInfo(recruiterInfo);
        post.setStatus(Status.PENDING);
        post.setPostedDate(LocalDate.now());
        postRepository.save(post);
    }

    public Post findById(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        return optionalPost.orElse(null);
    }

    public void deleteById(Long id) {
        if (applicationRepository.existsByPost(findById(id))) {
            throw new DeleteAppliedPostException("Không thể xóa bài đăng đã được ứng tuyển");
        }
        postRepository.deleteById(id);
    }

    public List<Post> findByStatus(Status status) {
        return postRepository.findByStatus(status);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public long countAll() {
        return postRepository.count();
    }

    public long countByStatus(Status status) {
        return postRepository.countByStatus(status);
    }

    public List<Post> findByUserInfo(UserInfo userInfo) {
        return postRepository.findByUserInfo(userInfo);
    }

    public void approve(Long id) {
        Post post = findById(id);
        if (post != null) {
            post.setStatus(Status.APPROVED);
            postRepository.save(post);
        }
    }

    public void reject(Long id) {
        Post post = findById(id);
        if (post != null) {
            post.setStatus(Status.REJECTED);
            postRepository.save(post);
        }
    }
}