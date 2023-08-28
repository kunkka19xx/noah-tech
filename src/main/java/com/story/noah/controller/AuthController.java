package com.story.noah.controller;

import com.story.noah.model.User;
import com.story.noah.payload.JwtAuthResponse;
import com.story.noah.payload.ResponseDto;
import com.story.noah.payload.SignInInput;
import com.story.noah.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseDto<JwtAuthResponse> signIn(@RequestBody SignInInput input){
        System.out.println("api login");
        ResponseDto<JwtAuthResponse> response = new ResponseDto<>();
        response.setData(authService.signIn(input));
        return response;
    }

    @PostMapping("/create")
    public ResponseDto<JwtAuthResponse> signIn(@RequestBody User user){
        System.out.println("api login");
        ResponseDto<JwtAuthResponse> response = new ResponseDto<>();
        response.setData(authService.signUp(user));
        return response;
    }
}
