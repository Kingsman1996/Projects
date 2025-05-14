package com.service;

import com.dto.EditPasswordForm;
import com.dto.LoginForm;
import com.dto.RegisterForm;
import com.entity.AuthInfo;
import com.entity.UserInfo;
import com.enums.Role;
import com.exception.InvalidPasswordException;
import com.exception.UsernameExistException;
import com.exception.UsernameNotFoundException;
import com.repo.UserInfoRepository;
import com.repo.AuthInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthInfoService {
    private final AuthInfoRepository authInfoRepository;
    private final UserInfoRepository userInfoRepository;

    public List<AuthInfo> findByRole(Role role) {
        return authInfoRepository.findByRole(role);
    }

    public List<AuthInfo> findByRoleNot(Role role) {
        return authInfoRepository.findByRoleNot(role);
    }

    public long countByRoleNot(Role role) {
        return authInfoRepository.countByRoleNot(role);
    }

    public long countByRole(Role role) {
        return authInfoRepository.countByRole(role);
    }

    public void register(RegisterForm registerForm) {
        if (existsByUsername(registerForm.getUsername())) {
            throw new UsernameExistException("Tài khoản đã tồn tại");
        }
        AuthInfo authInfo = new AuthInfo();
        authInfo.setUsername(registerForm.getUsername());
        authInfo.setPassword(registerForm.getPassword());
        authInfo.setRole(registerForm.getRole());
        authInfoRepository.save(authInfo);
        UserInfo userInfo = new UserInfo();
        userInfo.setAuthInfo(authInfo);
        userInfoRepository.save(userInfo);
    }

    public AuthInfo login(LoginForm loginForm) {
        String inputUsername = loginForm.getUsername();
        Optional<AuthInfo> optionalAuthInfo = authInfoRepository.findByUsername(inputUsername);
        if (!optionalAuthInfo.isPresent()) {
            throw new UsernameNotFoundException("Sai tài khoản");
        }
        AuthInfo authInfo = optionalAuthInfo.get();
        if (!authInfo.getPassword().equals(loginForm.getPassword())) {
            throw new InvalidPasswordException("Sai mật khẩu");
        }
        return authInfo;
    }

    public AuthInfo editPassword(AuthInfo authInfo, EditPasswordForm editPasswordForm) {
        authInfo.setPassword(editPasswordForm.getNewPassword());
        authInfoRepository.save(authInfo);
        return authInfo;
    }

    private boolean existsByUsername(String username) {
        return authInfoRepository.existsByUsername(username);
    }
}
