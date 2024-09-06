package com.tac.taskmanagement.entity;

import com.tac.taskmanagement.dto.Project;
import com.tac.taskmanagement.dto.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "taskmanagement")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "day_of_week", nullable = false)
    private String dayOfWeek;

    @Column(name = "time", nullable = false)
    private LocalTime time;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "status", nullable = false, length = 100)
    private String status;

    @Column(name = "task", columnDefinition = "TEXT")
    private String task;

    @Column(name = "log_hours")
    private String logHours;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity assignedUser;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectEntity project;


}

