package com.service;

import com.model.application.Application;
import com.model.application.ApplicationStatus;
import com.model.post.Post;
import com.model.post.PostStatus;
import com.model.user.UserInfo;
import com.model.user.User;
import com.repo.ApplicationRepository;
import com.repo.UserInfoRepository;
import com.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final ApplicationRepository applicationRepository;
    private final UserInfoRepository userInfoRepository;

    @Autowired
    public PostService(PostRepository postRepository,
                       ApplicationRepository applicationRepository,
                       UserInfoRepository userInfoRepository) {
        this.postRepository = postRepository;
        this.applicationRepository = applicationRepository;
        this.userInfoRepository = userInfoRepository;
    }

    public void save(Post post) {
        if (post.getStatus() == null) {
            post.setStatus(PostStatus.PENDING);
        }
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

    public Object countAll() {
        return postRepository.count();
    }

    public int countByStatus(PostStatus status) {
        return postRepository.countByStatus(status);
    }

    public void applyForPost(Long id, User candidate, MultipartFile file) {
        Application application = new Application();
        application.setCandidate(candidate);
        application.setPost(findById(id));
        application.setStatus(ApplicationStatus.SUBMITTED);
        application.setAppliedAt(LocalDate.now());
        application.setCvLink(file.getOriginalFilename());
        applicationRepository.save(application);
    }
}
