package com.service;

import com.entity.Application;
import com.entity.Post;
import com.entity.UserInfo;
import com.repo.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
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

    public void save(Long id, HttpSession session, MultipartFile cvFile) throws IOException {
        Application application = new Application();
        application.setPost(postService.findById(id));
        UserInfo candidateInfo = (UserInfo) session.getAttribute("userInfo");
        application.setUserInfo(candidateInfo);
        application.setAppliedAt(LocalDate.now());
        String fileName = cvFile.getOriginalFilename();
        String uploadDir = "uploads/cv/";
        File dest = new File(uploadDir + fileName);
        cvFile.transferTo(dest);
        application.setCvLink(fileName);
        applicationRepository.save(application);
    }
}
