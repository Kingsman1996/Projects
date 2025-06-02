package com.teamtaskmanager.service;

import com.teamtaskmanager.dto.LoginRequest;
import com.teamtaskmanager.dto.RegisterRequest;
import com.teamtaskmanager.entity.Account;
import com.teamtaskmanager.entity.Authority;
import com.teamtaskmanager.entity.AccountInfo;
import com.teamtaskmanager.exception.AuthException;
import com.teamtaskmanager.exception.DuplicateException;
import com.teamtaskmanager.repository.AccountRepository;
import com.teamtaskmanager.repository.AuthorityRepository;
import com.teamtaskmanager.repository.AccountInfoRepository;
import com.teamtaskmanager.repository.RoleRepository;
import com.teamtaskmanager.util.MessageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final AuthorityRepository authorityRepository;
    private final AccountInfoRepository accountInfoRepository;
    private final RoleRepository roleRepository;
    private final MessageHelper messageHelper;
    private final JwtService jwtService;

    public String login(LoginRequest loginRequest) {
        String inputUsername = loginRequest.getUsername();
        String inputPassword = loginRequest.getPassword();
        Account foundAccount = accountRepository.findByUsername(inputUsername);
        if (foundAccount == null || !foundAccount.getPassword().equals(inputPassword)) {
            throw new AuthException(messageHelper.get("auth.fail"));
        }
        return jwtService.generateToken(foundAccount);
    }

    public void register(RegisterRequest registerRequest) {
        String inputUsername = registerRequest.getUsername();
        String inputPassword = registerRequest.getPassword();

        if (existedUsername(inputUsername)) {
            throw new DuplicateException(messageHelper.get("username.exist"));
        }

        Account account = new Account();
        account.setUsername(inputUsername);
        account.setPassword(inputPassword);

        Authority authority = new Authority();
        authority.setAccount(account);
        authority.setRole(roleRepository.findByName((registerRequest.getRole())));

        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setAccount(account);

        accountRepository.save(account);
        authorityRepository.save(authority);
        accountInfoRepository.save(accountInfo);
    }

    private boolean existedUsername(String username) {
        return accountRepository.existsByUsername(username);
    }
}