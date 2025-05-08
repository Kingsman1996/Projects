package com.service;

import com.model.application.Application;
import com.model.user.*;
import com.repo.UserInfoRepository;
import com.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;

    @Autowired
    public UserService(UserRepository userRepository, UserInfoRepository userInfoRepository) {
        this.userRepository = userRepository;
        this.userInfoRepository = userInfoRepository;
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public List<User> findByRole(UserRole role) {
        return userRepository.findByRole(role);
    }

    public long count() {
        return userRepository.count();
    }

    public long countByRole(UserRole role) {
        return userRepository.countByRole(role);
    }

    @Transactional
    public void register(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Tài khoản đã tồn tại");
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setUser(user);
        userRepository.save(user);
        userInfoRepository.save(userInfo);
    }

    public boolean checkLogin(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(value -> value.getPassword().equals(password)).orElse(false);
    }

    @Transactional
    public void update(String username, UserUpdateForm updateForm) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            User foundUser = userOptional.get();
            foundUser.setPassword(updateForm.getPassword());
            foundUser.getUserInfo().setFirstName(updateForm.getFirstName());
            foundUser.getUserInfo().setLastName(updateForm.getLastName());
            foundUser.getUserInfo().setEmail(updateForm.getEmail());
            foundUser.getUserInfo().setPhone(updateForm.getPhone());
            foundUser.getUserInfo().setAddress(updateForm.getAddress());
            userRepository.save(foundUser);
            userInfoRepository.save(foundUser.getUserInfo());
        }
    }

}
