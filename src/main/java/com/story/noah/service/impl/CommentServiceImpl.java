package com.story.noah.service.impl;

import com.story.noah.dto.CommentProjection;
import com.story.noah.model.Comment;
import com.story.noah.repository.jpa.CommentRepository;
import com.story.noah.service.AuthService;
import com.story.noah.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private AuthService authService;

    @Transactional
    @Override
    public boolean postComment(Comment comment) {
        try {
            comment.setCreatedAt(LocalDateTime.now());
            comment.setUpdatedAt(LocalDateTime.now());
            comment.setUser(authService.getCurrentUser());
            commentRepository.save(comment);
        } catch (DataIntegrityViolationException e) {
            return false;
        }
        return true;
    }

    @Override
    public Page<CommentProjection> getCommentByPost(int postId, Pageable pageable) {
        Sort sort = Sort.by(Sort.Direction.DESC,"createdAt");
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        return commentRepository.findCommentProjectionByPostId(postId, pageable);
    }
}
