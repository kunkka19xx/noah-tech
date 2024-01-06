package com.story.noah.controller;

import com.story.noah.dto.MiniPostDto;
import com.story.noah.dto.PostCreationDto;
import com.story.noah.dto.PostProjection;
import com.story.noah.model.Post;
import com.story.noah.payload.Filter;
import com.story.noah.payload.ResponseDto;
import com.story.noah.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.beans.BeanProperty;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/post")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/create")
    public boolean createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }

    @GetMapping("/public/all")
    public ResponseDto<Page<PostProjection>> getAll(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "10") int size) {
        PageRequest pageable = PageRequest.of(page, size);
        ResponseDto<Page<PostProjection>> response = new ResponseDto<>();
        Page<PostProjection> data = postService.getAll(pageable);
        response.setData(data);
        response.setMessage("DONE");
        response.setHttpStatusCode(HttpStatus.FOUND.value());
        return response;
    }

    @GetMapping("/public/detail/{id}")
    public ResponseDto<PostProjection> findById(@PathVariable(value = "id") int id) {
        ResponseDto<PostProjection> response = new ResponseDto<>();
        Optional<PostProjection> post = postService.findPostProjectionById(id);
        if (post.isPresent()) {
            response.setHttpStatusCode(HttpStatus.OK.value());
            response.setData(post.get());
            response.setMessage("Post was get");
        } else {
            response.setHttpStatusCode(HttpStatus.NOT_FOUND.value());
            response.setMessage("Post's not found");
        }
        return response;
    }

    @GetMapping("/public/mini")
    public ResponseDto<Page<MiniPostDto>> getMiniPost(@RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int size, Integer userId, String title) {
        PageRequest pageable = PageRequest.of(page, size);
        ResponseDto<Page<MiniPostDto>> response = new ResponseDto<>();
        Filter filter = new Filter(userId, title, null, null);
        Page<MiniPostDto> data = postService.getMiniPost(pageable, filter);
        response.setData(data);
        response.setMessage("DONE");
        response.setHttpStatusCode(HttpStatus.FOUND.value());
        return response;
    }

    @GetMapping("/public/latest")
    public ResponseDto<List<MiniPostDto>> getLatestPost(Integer userId, String title) {
        ResponseDto<List<MiniPostDto>> response = new ResponseDto<>();
        Filter filter = new Filter(userId, title, null, null);
        List<MiniPostDto> data = postService.getLatestPost(filter);
        response.setData(data);
        response.setMessage("DONE");
        response.setHttpStatusCode(HttpStatus.FOUND.value());
        return response;
    }

    @GetMapping("/public/top")
    public ResponseDto<List<MiniPostDto>> getTopPost(Integer userId, String title) {
        ResponseDto<List<MiniPostDto>> response = new ResponseDto<>();
        Filter filter = new Filter(userId, title, null, null);
        List<MiniPostDto> data = postService.getLatestPost(filter);
        response.setData(data);
        response.setMessage("DONE");
        response.setHttpStatusCode(HttpStatus.FOUND.value());
        return response;
    }

    @PostMapping("/make-post")
    public ResponseDto<Post> makePostByDto(@ModelAttribute PostCreationDto post) {
        Post data = postService.makePost(post);
        ResponseDto<Post> result = new ResponseDto<Post>();
        result.setData(data);
        result.setHttpStatusCode(data == null ? HttpStatus.BAD_REQUEST.value() : HttpStatus.CREATED.value());
        if(data != null){
            System.out.println(data.getId());
        }
        return result;
    }

    /**
     * TODO
     */
    @GetMapping("/public/find")
    public ResponseDto<Page<MiniPostDto>> findPostByConditions(@RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "10") int size, Integer userId,
                                                               String title, String tag, String category) {
        PageRequest pageable = PageRequest.of(page, size);
        ResponseDto<Page<MiniPostDto>> response = new ResponseDto<>();
        Filter filter = new Filter(userId, title, tag, category);
        Page<MiniPostDto> data = postService.getMiniPost(pageable, filter);
        response.setData(data);
        response.setMessage("DONE");
        response.setHttpStatusCode(HttpStatus.FOUND.value());
        return response;
    }

}

