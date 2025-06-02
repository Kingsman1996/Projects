package com.teamtaskmanager.service;

import com.teamtaskmanager.entity.Role;
import com.teamtaskmanager.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService implements CommandLineRunner {
    private final RoleRepository roleRepository;
    @Value("${role.admin}")
    private String adminRole;

    @Value("${role.member}")
    private String memberRole;

    @Value("${role.manager}")
    private String managerRole;

    @Override
    public void run(String... args) {
        for (String item : List.of(adminRole, memberRole, managerRole)) {
            if (!roleRepository.existsByName(item)) {
                Role role = new Role();
                role.setName(item);
                roleRepository.save(role);
            }
        }
    }
}
