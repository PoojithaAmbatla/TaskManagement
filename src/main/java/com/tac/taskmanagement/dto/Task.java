package com.tac.taskmanagement.dto;

import lombok.Data;

@Data
public class Task {
    private String name;
    private String status;
    private String task;
    private String logHours;
}
