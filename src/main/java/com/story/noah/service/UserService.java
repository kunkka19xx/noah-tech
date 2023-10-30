package com.story.noah.service;

import com.story.noah.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    boolean createUser(User user);

    Page<User> getAllUser(Pageable pageable);

}
