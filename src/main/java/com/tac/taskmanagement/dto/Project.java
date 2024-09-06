package com.tac.taskmanagement.dto;

import lombok.Data;

@Data
public class Project {
    private Long id;
    private String name;
    private String description;
    private String status;
    private Long clientId;
}
