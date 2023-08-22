package com.story.noah.dto;

import com.story.noah.model.PartOfPost;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.util.List;

public interface PostWithUserIdProjection {
    Integer getId();

    String getTitle();

    String getDescription();

    String[] getCategories();

    String[] getTags();

    int getViewNumber();

    @Value("#{target.user.id}")
    Integer getUserId();

    LocalDateTime getCreatedAt();

    LocalDateTime getUpdatedAt();

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