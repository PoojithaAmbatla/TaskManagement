package com.tac.taskmanagement.service;

import com.tac.taskmanagement.dto.Client;
import com.tac.taskmanagement.dto.Project;
import com.tac.taskmanagement.dto.Task;
import com.tac.taskmanagement.dto.User;
import com.tac.taskmanagement.entity.ClientEntity;
import com.tac.taskmanagement.entity.ProjectEntity;
import com.tac.taskmanagement.entity.TaskEntity;
import com.tac.taskmanagement.entity.UserEntity;
import com.tac.taskmanagement.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    private static final List<String> allowedNames = Arrays.asList("Chay", "Nithin", "Pradeep", "Souji", "Sathvika P", "Shivu");
    private static final Pattern LOG_HOURS_PATTERN = Pattern.compile("(\\d+)hrs(?: (\\d+)min)?");

    public TaskEntity saveData(String name, String status, String task, String logHours) {
        validateLogHours(logHours);

        if (!allowedNames.contains(name)) {
            throw new IllegalArgumentException("Name not allowed: " + name);
        }
        TaskEntity entity = new TaskEntity();
        entity.setDate(LocalDate.now());
        entity.setDayOfWeek(LocalDate.now().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH));
        entity.setTime(LocalTime.now().withSecond(0).withNano(0));
        entity.setName(name);
        entity.setStatus(status);
        entity.setTask(task);
        entity.setLogHours(logHours);

        return taskRepository.save(entity);
    }

    public List<Task> getAllData() {
        List<TaskEntity> taskEntities = taskRepository.findAll();
        return taskEntities.stream().map(taskEntity -> {
            Task taskDto = new Task();
            taskDto.setId(taskEntity.getId());
            taskDto.setName(taskEntity.getName());
            taskDto.setStatus(taskEntity.getStatus());
            taskDto.setTask(taskEntity.getTask());
            taskDto.setLogHours(taskEntity.getLogHours());

            // Convert ProjectEntity to Project DTO
            ProjectEntity projectEntity = taskEntity.getProject();
            if (projectEntity != null) {
                Project projectDto = new Project();
                projectDto.setId(projectEntity.getId());
                projectDto.setName(projectEntity.getName());
                projectDto.setDescription(projectEntity.getDescription());
                projectDto.setStatus(projectEntity.getStatus());

                // Convert ClientEntity to Client DTO
                ClientEntity clientEntity = projectEntity.getClient();
                if (clientEntity != null) {
                    Client clientDto = new Client();
                    clientDto.setId(clientEntity.getId());
                    clientDto.setName(clientEntity.getName());
                    taskDto.setClient(clientDto);
                }

                taskDto.setProject(projectDto);
            }

            // Convert UserEntity to User DTO
            UserEntity userEntity = taskEntity.getAssignedUser();
            if (userEntity != null) {
                User userDto = new User();
                userDto.setId(userEntity.getId());
                userDto.setName(userEntity.getName());
                userDto.setEmail(userEntity.getEmail());
                taskDto.setAssignedUser(userDto);
            }

            return taskDto;
        }).collect(Collectors.toList());
    }
    public TaskEntity getDataById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public List<TaskEntity> getDataByName(String name) {
        return taskRepository.findByName(name);
    }

    public List<TaskEntity> getDataByDate(LocalDate date) {
        return taskRepository.findByDate(date);
    }

    public TaskEntity updateData(Long id, Task task) {
        validateLogHours(task.getLogHours());

        return taskRepository.findById(id).map(entity -> {
            if (!allowedNames.contains(task.getName())) {
                throw new IllegalArgumentException("Name not allowed: " + task.getName());
            }
            entity.setName(task.getName());
            entity.setStatus(task.getStatus());
            entity.setTask(task.getTask());
            entity.setLogHours(task.getLogHours());
            entity.setDate(LocalDate.now());
            entity.setTime(LocalTime.now());
            entity.setDayOfWeek(LocalDate.now().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH));
            return taskRepository.save(entity);
        }).orElse(null);
    }

    private void validateLogHours(String logHours) {
        Matcher matcher = LOG_HOURS_PATTERN.matcher(logHours.trim());

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid log hours format. Expected format: 'Xhrs Ymin' or 'Xhrs'");
        }

        int hours = Integer.parseInt(matcher.group(1));
        String minutesGroup = matcher.group(2);
        int minutes = (minutesGroup != null) ? Integer.parseInt(minutesGroup) : 0;

        if (hours > 8 || (hours == 8 && minutes > 0)) {
            throw new IllegalArgumentException("Log hours cannot exceed 8 hours.");
        }
    }


    public void addName(String name){
        if(!allowedNames.contains(name)){
            allowedNames.add(name);
        }
    }

    public void deleteData(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new EntityNotFoundException("Entity with ID " + id + " not found.");
        }
        taskRepository.deleteById(id);
    }
}
