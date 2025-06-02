package com.teamtaskmanager.service;

import com.teamtaskmanager.dto.ProjectRequest;
import com.teamtaskmanager.entity.Project;
import com.teamtaskmanager.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;

    public void add(ProjectRequest projectRequest) {
        Project project = new Project();
        project.setName(projectRequest.getName());
        project.setDescription(projectRequest.getDescription());
        project.setActive(true);
        project.setStartDate(new Date());
        projectRepository.save(project);
    }

    public Project findById(Long id) {
        return projectRepository.findById(id).orElse(null);
    }

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public void delete(Long id) {
        projectRepository.deleteById(id);
    }
}
