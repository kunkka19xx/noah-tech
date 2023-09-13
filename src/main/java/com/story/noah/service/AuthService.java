package com.story.noah.service;

import com.story.noah.model.User;
import com.story.noah.payload.JwtAuthResponse;
import com.story.noah.payload.SignInInput;

public interface AuthService {
    JwtAuthResponse signIn(SignInInput input);

    JwtAuthResponse signUp(User input);

    User getCurrentUser();
}
