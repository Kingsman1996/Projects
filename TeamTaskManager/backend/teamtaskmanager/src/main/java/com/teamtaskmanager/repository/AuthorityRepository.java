package com.teamtaskmanager.repository;

import com.teamtaskmanager.entity.Account;
import com.teamtaskmanager.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    List<Authority> findByAccount(Account account);
}
