package com.repo;

import com.entity.AuthInfo;
import com.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByAuthInfo(AuthInfo authInfo);

    boolean existsByPhone(String phone);
    boolean existsByEmail(String email);
}
