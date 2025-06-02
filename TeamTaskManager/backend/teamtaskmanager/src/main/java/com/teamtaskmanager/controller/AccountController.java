package com.teamtaskmanager.controller;

import com.teamtaskmanager.dto.LoginRequest;
import com.teamtaskmanager.dto.RegisterRequest;
import com.teamtaskmanager.service.AccountService;
import com.teamtaskmanager.util.BindingHandler;
import com.teamtaskmanager.util.MessageHelper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final MessageHelper messageHelper;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(accountService.login(loginRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest,
                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(Map.of("error", BindingHandler.getErrorMessages(bindingResult)));
        }
        accountService.register(registerRequest);
        return ResponseEntity.ok(messageHelper.get("register.success"));
    }
}
