package com.teamtaskmanager.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProjectRequest {
    @NotBlank(message = "{name.notBlank}")
    private String name;

    private String description;
}
