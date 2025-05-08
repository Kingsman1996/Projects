package com.repo;

import com.model.user.User;
import com.model.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    List<User> findByRole(UserRole role);

    boolean existsByUsername(String username);

    long countByRole(UserRole role);

}
