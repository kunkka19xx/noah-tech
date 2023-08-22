package com.story.noah.service.impl;

import com.story.noah.dto.MiniPostDto;
import com.story.noah.dto.PostWithUserIdProjection;
import com.story.noah.model.PartOfPost;
import com.story.noah.model.Post;
import com.story.noah.payload.Filter;
import com.story.noah.repository.jpa.PartOfPostRepository;
import com.story.noah.repository.jpa.PostRepository;
import com.story.noah.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PartOfPostRepository partRepository;

    @Override
    public boolean createPost(Post post) {
        List<PartOfPost> part = post.getContent();

        postRepository.save(post);
        partRepository.saveAll(part);
        return true;
    }

    @Override
    public Page<PostWithUserIdProjection> getAll(Pageable pageable) {
        return postRepository.findAllProjectedBy(pageable);
    }

    @Override
    public Page<MiniPostDto> getMiniPost(Pageable pageable, Filter filter) {
        return postRepository.getMiniPost(pageable, filter);
    }
}
