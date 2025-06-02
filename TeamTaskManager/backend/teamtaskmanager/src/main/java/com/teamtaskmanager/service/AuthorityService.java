package com.teamtaskmanager.service;

import com.teamtaskmanager.repository.AuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorityService  {
    private final AuthorityRepository authorityRepository;
}