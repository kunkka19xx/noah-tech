package com.story.noah.service.impl;

import com.story.noah.config.JwtUtils;
import com.story.noah.config.UserSecurity;
import com.story.noah.model.User;
import com.story.noah.payload.JwtAuthResponse;
import com.story.noah.payload.SignInInput;
import com.story.noah.repository.jpa.UserRepository;
import com.story.noah.service.AuthService;
import com.story.noah.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public JwtAuthResponse signIn(SignInInput input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(input.getEmail(), input.getPassword()));
        UserDetails user = userDetailService.userDetailsService().loadUserByUsername(input.getEmail());
        String jwt = jwtUtils.generateToken(user);
        return JwtAuthResponse.builder().token(jwt).build();
    }

    @Override
    @Transactional
    public JwtAuthResponse signUp(User input) {
        input.setUpdatedAt(LocalDateTime.now());
        input.setCreatedAt(LocalDateTime.now());
        input.setPassword(passwordEncoder.encode(input.getPassword()));
        Optional<User> user = Optional.of(userRepository.save(input));
        UserDetails userDetail = user.map(UserSecurity::new)
                .orElseThrow(() -> new IllegalArgumentException("Can not create user"));
        var jwt = jwtUtils.generateToken(userDetail);
        return new JwtAuthResponse(jwt);
    }
}
