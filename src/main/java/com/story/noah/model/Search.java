package com.story.noah.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "search", schema = "noah_tech")
public class Search {
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    private String content;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

}
