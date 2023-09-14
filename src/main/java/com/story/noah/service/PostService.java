package com.story.noah.service;

import com.story.noah.dto.MiniPostDto;
import com.story.noah.dto.PostCreationDto;
import com.story.noah.dto.PostProjection;
import com.story.noah.model.Post;
import com.story.noah.payload.Filter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface PostService {
    boolean createPost(Post post);

    Page<PostProjection> getAll(Pageable pageable);

    Page<MiniPostDto> getMiniPost(Pageable pageable, Filter filter);

    Post makePost(PostCreationDto post);

    Optional<PostProjection> findPostProjectionById(Integer id);

    List<MiniPostDto> getLatestPost(Filter filter);
}
