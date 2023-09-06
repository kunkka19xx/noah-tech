package com.story.noah.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonManagedReference
    @OneToMany(mappedBy = "post")
    List<PartOfPost> content;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
