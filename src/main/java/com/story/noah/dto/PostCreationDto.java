package com.story.noah.dto;

import com.story.noah.model.PartOfPost;
import com.story.noah.model.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PostCreationDto {

    private Integer id;
    private String title;
    private String description;

    private String[] categories;
    private String[] tags;
    private int viewNumber;

    private Integer userId;

    List<PartCreationDto> content;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
