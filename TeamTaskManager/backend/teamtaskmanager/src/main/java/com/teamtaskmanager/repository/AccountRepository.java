package com.teamtaskmanager.repository;

import com.teamtaskmanager.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    boolean existsByUsername(String username);
    Account findByUsername(String username);
}
