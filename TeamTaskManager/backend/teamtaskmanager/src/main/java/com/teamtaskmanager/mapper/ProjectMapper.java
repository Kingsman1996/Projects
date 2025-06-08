package com.teamtaskmanager.mapper;

import com.teamtaskmanager.dto.project.ProjectRequest;
import com.teamtaskmanager.entity.Project;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
     Project toEntity(ProjectRequest projectRequest);
     ProjectRequest toDTO(Project project);
}
