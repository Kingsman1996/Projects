package com.teamtaskmanager.service;

import com.teamtaskmanager.dto.AccountInfoRequest;
import com.teamtaskmanager.dto.AccountInfoResponse;
import com.teamtaskmanager.entity.Account;
import com.teamtaskmanager.entity.AccountInfo;
import com.teamtaskmanager.exception.DuplicateException;
import com.teamtaskmanager.repository.AccountRepository;
import com.teamtaskmanager.repository.AccountInfoRepository;
import com.teamtaskmanager.util.MessageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountInfoService {
    private final AccountInfoRepository accountInfoRepository;
    private final AccountRepository accountRepository;
    private final MessageHelper messageHelper;

    public void update(AccountInfoRequest accountInfoRequest) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Account foundAccount = accountRepository.findByUsername(username);
        if (emailExisted(accountInfoRequest.getEmail(), foundAccount)) {
            throw new DuplicateException(messageHelper.get("email.exist"));
        }

        if (phoneExisted(accountInfoRequest.getPhone(), foundAccount)) {
            throw new DuplicateException(messageHelper.get("phone.exist"));
        }

        AccountInfo foundAccountInfo = accountInfoRepository.findByAccount(foundAccount);
        foundAccountInfo.setName(accountInfoRequest.getName());
        foundAccountInfo.setEmail(accountInfoRequest.getEmail());
        foundAccountInfo.setPhone(accountInfoRequest.getPhone());
        accountInfoRepository.save(foundAccountInfo);
    }

    public List<AccountInfoResponse> findAll() {
        List<AccountInfo> accountInfoList = accountInfoRepository.findAll();
        return accountInfoList.stream()
                .map(accountInfo -> {
                    AccountInfoResponse response = new AccountInfoResponse();
                    response.setId(accountInfo.getId());
                    response.setUsername(accountInfo.getAccount().getUsername());
                    response.setName(accountInfo.getName());
                    response.setEmail(accountInfo.getEmail());
                    response.setPhone(accountInfo.getPhone());
                    return response;
                })
                .toList();
    }

    boolean emailExisted(String email, Account account) {
        return accountInfoRepository.existsByEmailAndAccountNot(email, account);
    }

    boolean phoneExisted(String phone, Account account) {
        return accountInfoRepository.existsByPhoneAndAccountNot(phone, account);
    }
}