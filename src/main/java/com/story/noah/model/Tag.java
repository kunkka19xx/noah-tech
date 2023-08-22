package com.story.noah.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "tag", schema = "noah_tech")
public class Tag {
    @Id
    private Integer id;
    
    private String name;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
