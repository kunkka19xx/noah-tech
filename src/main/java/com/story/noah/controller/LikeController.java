package com.story.noah.controller;

import com.story.noah.model.Like;
import com.story.noah.payload.LikeInput;
import com.story.noah.payload.ResponseDto;
import com.story.noah.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/like")
public class LikeController {
    @Autowired
    private LikeService likeService;

    @PostMapping("/process-post")
    public ResponseDto<Like> processLikePost(@RequestBody LikeInput input){
        ResponseDto<Like> response = new ResponseDto<>();

        response.setData(likeService.processLikePost(input));
        response.setHttpStatusCode(HttpStatus.CREATED.value());
        response.setMessage("LIKED");
        return response;
    }
}
