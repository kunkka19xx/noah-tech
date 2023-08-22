package com.story.noah.service;

import com.story.noah.dto.CommentProjection;
import com.story.noah.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface CommentService {

    boolean postComment(Comment comment);

    Page<CommentProjection> getCommentByPost(int postId, Pageable pageable);
}
