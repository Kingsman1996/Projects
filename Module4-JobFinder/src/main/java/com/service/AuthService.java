package com.service;

import com.dto.EditPasswordForm;
import com.dto.LoginForm;
import com.dto.RegisterForm;
import com.entity.Auth;
import com.entity.UserInfo;
import com.enums.Role;
import com.exception.UsernameExistException;
import com.exception.AuthException;
import com.repo.UserInfoRepository;
import com.repo.AuthInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthInfoRepository authInfoRepository;
    private final UserInfoRepository userInfoRepository;

    public List<Auth> findByRole(Role role) {
        return authInfoRepository.findByRole(role);
    }

    public List<Auth> findByRoleNot(Role role) {
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
            throw new UsernameExistException("Tài khoản đã tồn tại", registerForm);
        }
        Auth auth = new Auth();
        auth.setUsername(registerForm.getUsername());
        auth.setPassword(registerForm.getPassword());
        auth.setRole(registerForm.getRole());
        authInfoRepository.save(auth);
        UserInfo userInfo = new UserInfo();
        userInfo.setAuth(auth);
        userInfoRepository.save(userInfo);
    }

    public Auth login(LoginForm loginForm) {
        String inputUsername = loginForm.getUsername();
        Optional<Auth> optionalAuthInfo = authInfoRepository.findByUsername(inputUsername);
        if (!optionalAuthInfo.isPresent()) {
            throw new AuthException("Sai tài khoản", loginForm);
        }
        Auth auth = optionalAuthInfo.get();
        if (!auth.getPassword().equals(loginForm.getPassword())) {
            throw new AuthException("Sai mật khẩu", loginForm);
        }
        return auth;
    }

    public void editPassword(Auth auth, EditPasswordForm editPasswordForm) {
        auth.setPassword(editPasswordForm.getNewPassword());
        authInfoRepository.save(auth);
    }

    private boolean existsByUsername(String username) {
        return authInfoRepository.existsByUsername(username);
    }

    public String getHomeUrlByAuth(Auth auth) {
        String url;
        switch (auth.getRole()) {
            case ADMIN:
                url = "redirect:/admin";
                break;
            case CANDIDATE:
                url = "redirect:/candidates";
                break;
            case RECRUITER:
                url = "redirect:/recruiters";
                break;
            default:
                url = "redirect:/auth/login";
                break;
        }
        return url;
    }
}
