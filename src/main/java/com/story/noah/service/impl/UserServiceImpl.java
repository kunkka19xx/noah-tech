package com.story.noah.service.impl;

import com.story.noah.model.User;
import com.story.noah.repository.jpa.UserRepository;
import com.story.noah.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public boolean createUser(User user) {
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
        return true;
    }

    @Override
    public Page<User> getAllUser(Pageable pageable) {
        List<User> users = userRepository.findAll();
        return new PageImpl<>(users, pageable, users.size());
    }
}
