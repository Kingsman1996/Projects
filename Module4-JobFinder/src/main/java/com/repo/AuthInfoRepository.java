package com.repo;

import com.entity.Auth;
import com.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthInfoRepository extends JpaRepository<Auth, Long> {
    Optional<Auth> findByUsername(String username);

    List<Auth> findByRole(Role role);

    List<Auth> findByRoleNot(Role role);

    boolean existsByUsername(String username);

    long countByRole(Role role);

    long countByRoleNot(Role role);
}
