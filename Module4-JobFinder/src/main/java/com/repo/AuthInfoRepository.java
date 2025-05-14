package com.repo;

import com.entity.AuthInfo;
import com.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthInfoRepository extends JpaRepository<AuthInfo, Long> {
    Optional<AuthInfo> findByUsername(String username);

    List<AuthInfo> findByRole(Role role);

    List<AuthInfo> findByRoleNot(Role role);

    boolean existsByUsername(String username);

    long countByRole(Role role);

    long countByRoleNot(Role role);
}
