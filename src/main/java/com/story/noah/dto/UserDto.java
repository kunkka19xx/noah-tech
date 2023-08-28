package com.story.noah.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class UserDto {
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
