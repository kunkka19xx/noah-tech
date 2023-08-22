package com.story.noah.dto;

import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;

public interface CommentProjection {
    Integer getId();

    String getContent();

//    @Value("#{target.user.id}")
//    Integer getUserId();

    @Value("#{target.user}")
    UserProjection getUser();

//    @Value("#{target.post.id}")
//    Integer getPostId();

    public interface UserProjection{
        Integer getId();
        String getUserName();
    }

    LocalDateTime getCreatedAt();

}
