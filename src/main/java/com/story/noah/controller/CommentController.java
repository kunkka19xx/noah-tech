package com.story.noah.controller;

import com.story.noah.dto.CommentProjection;
import com.story.noah.model.Comment;
import com.story.noah.payload.ResponseDto;
import com.story.noah.repository.jpa.CommentRepository;
import com.story.noah.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("post")
    ResponseDto<Comment> postComment(@RequestBody Comment comment) {
        boolean isPosted = commentService.postComment(comment);
        ResponseDto<Comment> response = new ResponseDto<Comment>();
        if (isPosted) {
            response.setData(comment);
            response.setHttpStatusCode(HttpStatus.CREATED.value());
            response.setMessage("Commented");
        } else {
            response.setData(null);
            response.setMessage("Cant comment");
            response.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
        }
        return response;

    }

    @GetMapping("{postId}")
    Page<CommentProjection> getCommentByPost(@PathVariable(value = "postId") int postId, int page, int size){
        PageRequest pageable = PageRequest.of(page,size);
        return commentService.getCommentByPost(postId, pageable);
    }

}
