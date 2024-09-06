package com.tac.taskmanagement.dto;

import com.tac.taskmanagement.entity.ClientEntity;
import com.tac.taskmanagement.entity.ProjectEntity;
import com.tac.taskmanagement.entity.UserEntity;
import com.tac.taskmanagement.repository.UserRepository;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class Task {
    private Long id;
    private String name;
    private String status;
    private String task;
    private String logHours;
    private Project project;
    private Client client;
    private User assignedUser;
    private LocalDate date;
    private String dayOfWeek;
    private LocalTime time;
}
