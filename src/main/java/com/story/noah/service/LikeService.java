package com.story.noah.service;

import com.story.noah.model.Like;
import com.story.noah.payload.LikeInput;

public interface LikeService {
    Like processLikePost(LikeInput input);
}
