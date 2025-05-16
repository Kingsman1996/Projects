package com.service;

import com.dto.EditUserInfoForm;
import com.entity.Auth;
import com.entity.UserInfo;
import com.exception.DuplicateDataException;
import com.repo.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserInfoService {
    private final UserInfoRepository userInfoRepository;

    public UserInfo findByAuthInfo(Auth auth) {
        Optional<UserInfo> optionalUserInfo = userInfoRepository.findByAuth(auth);
        return optionalUserInfo.orElse(null);
    }

    public UserInfo update(UserInfo userInfo, EditUserInfoForm editUserInfoForm) {
        if (existsByEmail(editUserInfoForm.getEmail(), userInfo.getId())) {
            throw new DuplicateDataException("Email đã tồn tại", editUserInfoForm);
        }
        if (existsByPhone(editUserInfoForm.getPhone(), userInfo.getId())) {
            throw new DuplicateDataException("Số điện thoại đã tồn tại", editUserInfoForm);
        }
        userInfo.setFirstName(editUserInfoForm.getFirstName());
        userInfo.setLastName(editUserInfoForm.getLastName());
        userInfo.setEmail(editUserInfoForm.getEmail());
        userInfo.setPhone(editUserInfoForm.getPhone());
        userInfo.setAddress(editUserInfoForm.getAddress());
        userInfoRepository.save(userInfo);
        return userInfo;
    }

    boolean existsByPhone(String phone, Long id) {
        return userInfoRepository.existsByPhoneAndIdNot(phone, id);
    }

    boolean existsByEmail(String email, Long id) {
        return userInfoRepository.existsByEmailAndIdNot(email, id);
    }
}
