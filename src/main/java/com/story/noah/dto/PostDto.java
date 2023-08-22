package com.story.noah.dto;

import com.story.noah.model.User;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;

@Data
public class PostDto {
    private Integer id;
    private String title;
    private String description;

    @Type(StringArrayType.class)
    @Column(name = "categories", columnDefinition = "character varying[]")
    private String[] categories;
    @Type(StringArrayType.class)
    @Column(name = "tags", columnDefinition = "character varying[]")
    private String[] tags;
    private int viewNumber;

    private User user;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
