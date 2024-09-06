package com.tac.taskmanagement.controller;

import com.tac.taskmanagement.entity.ProjectEntity;
import com.tac.taskmanagement.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired ProjectService projectService;

    @PostMapping
    public ResponseEntity<ProjectEntity> createProject(@RequestBody ProjectEntity projectEntity){
        ProjectEntity createdProject = projectService.saveProject(projectEntity);
        return ResponseEntity.ok(createdProject);
    }
    @GetMapping
    public ResponseEntity<List<ProjectEntity>> getAllProjects(){
        List<ProjectEntity> projects = projectService.getAllProjects();
        return ResponseEntity.ok(projects);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProjectEntity> getProjectById(@PathVariable Long id){
        ProjectEntity project = projectService.getProjectById(id);
        if(project == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(project);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProjectEntity> updateProject(@PathVariable Long id, @RequestBody ProjectEntity projectEntity) {
        ProjectEntity updatedProject = projectService.updateProject(id, projectEntity);
        if (updatedProject == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedProject);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ProjectEntity> deleteProject(@PathVariable Long id){
        try{
            projectService.deleteProject(id);
            return ResponseEntity.noContent().build();
        }
        catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
