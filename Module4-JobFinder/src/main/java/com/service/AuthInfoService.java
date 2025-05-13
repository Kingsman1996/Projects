package com.service;

import com.dto.LoginRequest;
import com.dto.RegisterRequest;
import com.dto.UserUpdateForm;
import com.entity.user.*;
import com.exception.UsernameExistException;
import com.repo.UserInfoRepository;
import com.repo.AuthInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthInfoService {
    private final AuthInfoRepository authInfoRepository;
    private final UserInfoRepository userInfoRepository;

    public AuthInfo findById(Long id) {
        return authInfoRepository.findById(id).orElse(null);
    }

    public AuthInfo findByUsername(String username) {
        return authInfoRepository.findByUsername(username).orElse(null);
    }

    public List<AuthInfo> findByRole(UserRole role) {
        return authInfoRepository.findByRole(role);
    }

    public long count() {
        return authInfoRepository.count();
    }

    public long countByRole(UserRole role) {
        return authInfoRepository.countByRole(role);
    }

    @Transactional
    public void register(RegisterRequest registerRequest) {
        if (existsByUsername(registerRequest.getUsername())) {
            throw new UsernameExistException("Tài khoản đã tồn tại");
        }
        AuthInfo authInfo = new AuthInfo();
        authInfo.setUsername(registerRequest.getUsername());
        authInfo.setPassword(registerRequest.getPassword());
        authInfo.setRole(registerRequest.getRole());
        authInfoRepository.save(authInfo);
        UserInfo userInfo = userInfoRepository.findByAuthInfo(authInfo).orElse(null);
        if (userInfo == null) {
            userInfo = new UserInfo();
            userInfo.setAuthInfo(authInfo);
            userInfoRepository.save(userInfo);
        }
    }

    public boolean checkLogin(LoginRequest loginRequest) {
        String inputUsername = loginRequest.getUsername();
        if (!existsByUsername(inputUsername)) {
            return false;
        }
        Optional<AuthInfo> optionalAuthenticationInfo = authInfoRepository.findByUsername(inputUsername);
        if (!optionalAuthenticationInfo.isPresent()) {
            return false;
        }
        AuthInfo authInfo = optionalAuthenticationInfo.get();
        return authInfo.getPassword().equals(loginRequest.getPassword());
    }

    public void changePassword(UserUpdateForm userUpdateForm) {
        Optional<AuthInfo> optionalAuthenticationInfo = authInfoRepository.findByUsername(userUpdateForm.getUsername());
        if (optionalAuthenticationInfo.isPresent()) {
            AuthInfo foundAuthInfo = optionalAuthenticationInfo.get();
            foundAuthInfo.setPassword(userUpdateForm.getPassword());
            authInfoRepository.save(foundAuthInfo);
        }
    }

    private boolean existsByUsername(String username) {
        return authInfoRepository.existsByUsername(username);
    }
}
