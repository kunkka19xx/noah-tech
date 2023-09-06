package com.story.noah.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@Setter
public class PartCreationDto {
    private Integer id;
    private Integer postId;
    private Short index;
    private String content;
    private MultipartFile file;
    private String image;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
