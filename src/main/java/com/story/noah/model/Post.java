package com.story.noah.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Type;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "post")
public class Post {
    @Id
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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post")
    List<PartOfPost> content;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
