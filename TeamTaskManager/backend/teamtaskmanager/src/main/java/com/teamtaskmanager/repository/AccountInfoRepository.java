package com.teamtaskmanager.repository;

import com.teamtaskmanager.entity.Account;
import com.teamtaskmanager.entity.AccountInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountInfoRepository extends JpaRepository<AccountInfo, Long> {
    boolean existsByEmailAndAccountNot(String email, Account account);

    boolean existsByPhoneAndAccountNot(String phone, Account account);

    AccountInfo findByAccount(Account account);
}