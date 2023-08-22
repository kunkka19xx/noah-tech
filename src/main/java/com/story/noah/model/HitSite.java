package com.story.noah.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "hit_site", schema = "noah_tech")
public class HitSite {
    @Id
    private Long id;
    
    private String ip;

    @Column(name = "hit_at")
    private OffsetDateTime hitAt;

    @Column(name = "user_id")
    private Integer userId;

    private String address;

}
