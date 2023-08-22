package com.story.noah.service;

import com.story.noah.dto.MiniPostDto;
import com.story.noah.dto.PostWithUserIdProjection;
import com.story.noah.model.Post;
import com.story.noah.payload.Filter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface PostService {
    boolean createPost(Post post);

    Page<PostWithUserIdProjection> getAll(Pageable pageable);

    Page<MiniPostDto> getMiniPost(Pageable pageable, Filter filter);
}
