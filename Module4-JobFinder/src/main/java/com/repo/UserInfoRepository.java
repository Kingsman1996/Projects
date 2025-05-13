package com.repo;

import com.entity.user.AuthInfo;
import com.entity.user.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByAuthInfo(AuthInfo authInfo);

    Optional<UserInfo> findById(Long id);
}
