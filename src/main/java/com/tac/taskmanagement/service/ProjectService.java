package com.tac.taskmanagement.service;

import com.tac.taskmanagement.entity.ProjectEntity;
import com.tac.taskmanagement.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public ProjectEntity saveProject(ProjectEntity project){
        return projectRepository.save(project);
    }

    public List<ProjectEntity> getAllProjects() {
        return projectRepository.findAll();
    }
    public ProjectEntity getProjectById(Long id){
        return projectRepository.findById(id).orElse(null);
    }
    public ProjectEntity updateProject(Long id, ProjectEntity project){
        return projectRepository.findById(id).map(existingProject -> {
            existingProject.setName(project.getName());
            existingProject.setDescription(project.getDescription());
            existingProject.setStatus(project.getStatus());
            return projectRepository.save(existingProject);
        }).orElse(null);
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

}
