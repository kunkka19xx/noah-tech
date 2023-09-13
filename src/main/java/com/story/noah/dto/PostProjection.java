package com.story.noah.dto;

import com.story.noah.model.PartOfPost;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.util.List;

public interface PostProjection {
    Integer getId();

    String getTitle();

    String getDescription();

    String[] getCategories();

    String[] getTags();

    int getViewNumber();

    @Value("#{target.user.id}")
    Integer getUserId();

    @Value("#{target.user.username}")
    String getAuthor();

    LocalDateTime getCreatedAt();

    LocalDateTime getUpdatedAt();

    Integer getLength();

//    List<PartOfPost> getContent();

    /**
     * Get content as list so we can represent more easier.
     *
     * @return content.
     */
    PartOfPostProjection[] getContent();

    /**
     * interface to get parts of post's content.
     */
    interface PartOfPostProjection {
        Integer getId();

        int getIndex();

        String getContent();

        String getImage();

    }

}