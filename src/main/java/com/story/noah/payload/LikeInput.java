package com.story.noah.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LikeInput {
    private Integer userId;
    private Integer postId;
    private Integer commentId;

    /**
     * 0: like on post.
     * -1: like on comment.
     */
    private int type;
}
