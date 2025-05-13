package com.service;

import com.dto.UserUpdateForm;
import com.entity.user.AuthInfo;
import com.entity.user.UserInfo;
import com.repo.AuthInfoRepository;
import com.repo.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserInfoService {
    private final UserInfoRepository userInfoRepository;
    private final AuthInfoRepository authInfoRepository;

    public UserInfo findById(Long id) {
        Optional<UserInfo> userInfo = userInfoRepository.findById(id);
        return userInfo.orElse(null);
    };

    public UserInfo findByAuthInfo(AuthInfo authInfo) {
        Optional<UserInfo> optionalUserInfo = userInfoRepository.findByAuthInfo(authInfo);
        return optionalUserInfo.orElse(null);
    }

    public void update(UserUpdateForm userUpdateForm) {
        AuthInfo foundAuthInfo = authInfoRepository.findByUsername(userUpdateForm.getUsername()).orElse(null);
        if (foundAuthInfo != null) {
            Optional<UserInfo> optionalUserInfo = userInfoRepository.findByAuthInfo(foundAuthInfo);
            if (optionalUserInfo.isPresent()) {
                UserInfo currentInfo = optionalUserInfo.get();
                currentInfo.setFirstName(userUpdateForm.getFirstName());
                currentInfo.setLastName(userUpdateForm.getLastName());
                currentInfo.setEmail(userUpdateForm.getEmail());
                currentInfo.setPhone(userUpdateForm.getPhone());
                currentInfo.setAddress(userUpdateForm.getAddress());
                userInfoRepository.save(currentInfo);
            }
        }
    }
}
