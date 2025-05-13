package com.service;

import com.entity.application.Application;
import com.entity.application.ApplicationStatus;
import com.entity.post.Post;
import com.entity.user.UserInfo;
import com.repo.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final PostService postService;

    public List<Application> findByPost(Post post) {
        return applicationRepository.findByPost(post);
    }

    public void save(Long postId, UserInfo candidateInfo, MultipartFile file) {
        Application application = new Application();
        application.setPost(postService.findById(postId));
        application.setUserInfo(candidateInfo);
        application.setStatus(ApplicationStatus.SUBMITTED);
        application.setAppliedAt(LocalDate.now());
        application.setCvLink(file.getOriginalFilename());
        applicationRepository.save(application);
    }
}
