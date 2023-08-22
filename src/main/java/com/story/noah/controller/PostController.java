package com.story.noah.controller;

import com.story.noah.aspect.Paging;
import com.story.noah.dto.MiniPostDto;
import com.story.noah.dto.PostDto;
import com.story.noah.dto.PostWithUserIdProjection;
import com.story.noah.model.Post;
import com.story.noah.payload.Filter;
import com.story.noah.payload.ResponseDto;
import com.story.noah.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/create")
    public boolean createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }

    @GetMapping("/all")
    public ResponseDto<Page<PostWithUserIdProjection>> getAll(@RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "10") int size) {
        PageRequest pageable = PageRequest.of(page,size);
        ResponseDto<Page<PostWithUserIdProjection>> response = new ResponseDto<>();
        Page<PostWithUserIdProjection> data = postService.getAll(pageable);
        response.setData(data);
        response.setMessage("DONE");
        response.setHttpStatusCode(HttpStatus.FOUND.value());
        return response;
    }

    @GetMapping("/mini")
    public ResponseDto<Page<MiniPostDto>> getMiniPost(@RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "10") int size, Integer userId, String title) {
        PageRequest pageable = PageRequest.of(page,size);
        ResponseDto<Page<MiniPostDto>> response = new ResponseDto<>();
        Filter filter = new Filter(userId, title);
        Page<MiniPostDto> data = postService.getMiniPost(pageable, filter);
        response.setData(data);
        response.setMessage("DONE");
        response.setHttpStatusCode(HttpStatus.FOUND.value());
        return response;
    }
}

