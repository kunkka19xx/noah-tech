package com.story.noah.model;

import com.fasterxml.jackson.databind.util.BeanUtil;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.time.OffsetTime;

@Data
@Entity
@Table(name = "category", schema = "noah_tech")
public class Category {
    @Id
    private Integer id;
    
    private String name;
    private String description;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}