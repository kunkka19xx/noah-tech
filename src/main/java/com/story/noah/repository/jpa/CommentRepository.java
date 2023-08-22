package com.story.noah.repository.jpa;

import com.story.noah.dto.CommentProjection;
import com.story.noah.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    Page<CommentProjection> findCommentProjectionByPostId(int postId, Pageable pageable);
}
