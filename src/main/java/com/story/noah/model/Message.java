package com.story.noah.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "message", schema = "noah_tech")
public class Message {
    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String content;
    private String subject;
    private String attach;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    private String email;
    private String phone;

}
