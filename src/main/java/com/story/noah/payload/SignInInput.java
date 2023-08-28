package com.story.noah.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInInput {
    private String email;
    private String password;
}
