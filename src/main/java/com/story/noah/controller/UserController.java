package com.story.noah.controller;

import com.story.noah.model.User;
import com.story.noah.payload.ResponseDto;
import com.story.noah.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public boolean createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @GetMapping("/all")
    ResponseDto<Page<User>> getAllUser(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "10") int size){
        PageRequest pageable = PageRequest.of(page,size);
        ResponseDto<Page<User>> response = new ResponseDto<>();
        Page<User> users = userService.getAllUser(pageable);
        if(users.getTotalElements()==0) response.setMessage("No User");
        response.setData(users);
        response.setHttpStatusCode(HttpStatus.OK.value());
        return response;
    }
}
