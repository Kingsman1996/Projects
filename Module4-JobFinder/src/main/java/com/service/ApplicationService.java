package com.service;

import com.entity.Application;
import com.entity.Post;
import com.entity.UserInfo;
import com.repo.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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

    public void save(Long id, UserInfo candidateInfo, MultipartFile cvFile) {
        Application application = new Application();
        application.setPost(postService.findById(id));
        application.setUserInfo(candidateInfo);
        application.setAppliedAt(LocalDate.now());
        String fileName = cvFile.getOriginalFilename();
        String uploadDir = "uploads/cv/";
        File dest = new File(uploadDir + fileName);
        try {
            cvFile.transferTo(dest);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        application.setCvLink(fileName);
        applicationRepository.save(application);
    }
}
