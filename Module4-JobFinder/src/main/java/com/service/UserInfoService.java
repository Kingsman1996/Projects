package com.service;

import com.model.user.UserInfo;
import com.model.user.User;
import com.repo.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoService {
    private final UserInfoRepository userInfoRepository;

    @Autowired
    public UserInfoService(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    public UserInfo findByUser(User user) {
        return userInfoRepository.findByUser(user).orElse(null);
    }

    public void save(UserInfo newInfo) {
        Optional<UserInfo> optionalContactInfo = userInfoRepository.findByUser(newInfo.getUser());
        if (optionalContactInfo.isPresent()) {
            UserInfo currentInfo = optionalContactInfo.get();
            currentInfo.setFirstName(newInfo.getFirstName());
            currentInfo.setLastName(newInfo.getLastName());
            currentInfo.setEmail(newInfo.getEmail());
            currentInfo.setPhone(newInfo.getPhone());
            currentInfo.setAddress(newInfo.getAddress());
            userInfoRepository.save(currentInfo);
        }
    }
    public UserInfo findByApplicationId(Long applicationId) {
        return userInfoRepository.findUserInfoByApplicationId(applicationId);
    }
}
