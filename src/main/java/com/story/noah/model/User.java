package com.story.noah.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String name;
    private String description;
    private String email;
    private String phone;
    private Date birthdate;
    private String password;
    private String token;
    private String address;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String[] roles;

}
