package com.teamtaskmanager.controller;


import com.teamtaskmanager.dto.AccountInfoRequest;
import com.teamtaskmanager.service.AccountInfoService;
import com.teamtaskmanager.util.BindingHandler;
import com.teamtaskmanager.util.MessageHelper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account-info")
public class AccountInfoController {
    private final AccountInfoService accountInfoService;
    private final MessageHelper messageHelper;

    @PutMapping()
    public ResponseEntity<?> update(@Valid @RequestBody AccountInfoRequest accountInfoRequest,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(Map.of("error", BindingHandler.getErrorMessages(bindingResult)));
        }
        accountInfoService.update(accountInfoRequest);
        return ResponseEntity.ok(messageHelper.get("account.info.update.success"));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<?> userList() {
        return ResponseEntity.ok(accountInfoService.findAll());
    }
}
