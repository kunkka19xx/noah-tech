package com.story.noah.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MiniPostDto {

    private Integer id;
    private String title;
    private Integer userId;
    private String author;
    private LocalDateTime createdAt;
    private String miniContent;
    private String image;
    private String[] tags;
    private String[] categories;

}
