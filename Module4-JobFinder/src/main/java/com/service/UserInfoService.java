package com.service;

import com.dto.EditUserInfoForm;
import com.entity.AuthInfo;
import com.entity.UserInfo;
import com.exception.DuplicateEmailExeption;
import com.exception.DuplicatePhoneException;
import com.repo.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserInfoService {
    private final UserInfoRepository userInfoRepository;

    public UserInfo findByAuthInfo(AuthInfo authInfo) {
        Optional<UserInfo> optionalUserInfo = userInfoRepository.findByAuthInfo(authInfo);
        return optionalUserInfo.orElse(null);
    }

    public UserInfo update(UserInfo userInfo, EditUserInfoForm editUserInfoForm) {
        if (existsByEmail(editUserInfoForm.getEmail())) {
            throw new DuplicateEmailExeption("Email đã tồn tại");
        }
        if (existsByPhone(editUserInfoForm.getPhone())) {
            throw new DuplicatePhoneException("Số điện thoại đã tồn tại");
        }
        userInfo.setFirstName(editUserInfoForm.getFirstName());
        userInfo.setLastName(editUserInfoForm.getLastName());
        userInfo.setEmail(editUserInfoForm.getEmail());
        userInfo.setPhone(editUserInfoForm.getPhone());
        userInfo.setAddress(editUserInfoForm.getAddress());
        userInfoRepository.save(userInfo);
        return userInfo;
    }

    public UserInfo findById(Long id) {
        Optional<UserInfo> optionalUserInfo = userInfoRepository.findById(id);
        return optionalUserInfo.orElse(null);
    }

    boolean existsByPhone(String phone) {
        return userInfoRepository.existsByPhone(phone);
    }

    boolean existsByEmail(String email) {
        return userInfoRepository.existsByEmail(email);
    }
}
