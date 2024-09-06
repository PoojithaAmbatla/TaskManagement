package com.tac.taskmanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "name", nullable = false, length = 100)
    private String name;

    @Column (name = "email", nullable = false, length = 100)
    private String email;

    @ManyToMany(mappedBy = "users")
    private List<ProjectEntity> projects;

    @OneToMany(mappedBy = "assignedUser")
    private List<TaskEntity> tasks;

}
