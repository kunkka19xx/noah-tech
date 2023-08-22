package com.story.noah.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.OffsetTime;

@Data
@Entity
@Table(name = "part_of_post")
public class PartOfPost {
    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    private Short index;
    private String content;
    private String image;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
