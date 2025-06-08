package com.teamtaskmanager.repository;

import com.teamtaskmanager.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    boolean existsByRole(Authority.Role role);

    Authority findByRole(Authority.Role role);

    List<Authority> findAllByRoleNot(Authority.Role role);
}
