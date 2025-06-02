package com.teamtaskmanager.controller;

import com.teamtaskmanager.dto.ProjectRequest;
import com.teamtaskmanager.service.ProjectService;
import com.teamtaskmanager.util.MessageHelper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    private final MessageHelper messageHelper;

    @GetMapping
    public ResponseEntity<?> getAllProjects() {
        return ResponseEntity.ok(projectService.findAll());
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @PostMapping
    public ResponseEntity<?> createProject(@Valid @RequestBody ProjectRequest projectRequest,
                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        projectService.add(projectRequest);
        return ResponseEntity.ok(messageHelper.get("project.created.success"));
    }
}
