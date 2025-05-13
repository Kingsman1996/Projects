package com.repo;

import com.entity.user.AuthInfo;
import com.entity.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthInfoRepository extends JpaRepository<AuthInfo, Long> {
    Optional<AuthInfo> findByUsername(String username);

    List<AuthInfo> findByRole(UserRole role);

    boolean existsByUsername(String username);

    long countByRole(UserRole role);
}
