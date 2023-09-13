package com.story.noah.service.impl;

import com.story.noah.config.JwtUtils;
import com.story.noah.config.UserSecurity;
import com.story.noah.exception.AlreadyExistException;
import com.story.noah.model.User;
import com.story.noah.payload.JwtAuthResponse;
import com.story.noah.payload.SignInInput;
import com.story.noah.repository.jpa.UserRepository;
import com.story.noah.service.AuthService;
import com.story.noah.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@PropertySource("classpath:message.properties")
@Service
public class AuthServiceImpl implements AuthService {

    @Value("${mess.err.username.exist}")
    String usernameExistMsg;

    @Value("${mess.err.email.exist}")
    String emailExistMsg;

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
        // check user is exist or not?
        if (userRepository.findByEmail(input.getUsername()).isPresent()) {
            // exception user with email is exist
            throw new AlreadyExistException(emailExistMsg);
        }
        if (userRepository.findByUsername(input.getUsername()).isPresent()) {
            // exception username is existed, please choose another name
            throw new AlreadyExistException(usernameExistMsg);
        }
        input.setUpdatedAt(LocalDateTime.now());
        input.setCreatedAt(LocalDateTime.now());
        input.setPassword(passwordEncoder.encode(input.getPassword()));
        Optional<User> user = Optional.of(userRepository.save(input));
        UserDetails userDetail = user.map(UserSecurity::new)
                .orElseThrow(() -> new IllegalArgumentException("Can not create user"));
        var jwt = jwtUtils.generateToken(userDetail);
        return new JwtAuthResponse(jwt);
    }

    @Override
    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            String currentUserName = auth.getName();
            return userRepository.findByEmail(currentUserName).get();
        } else {
            throw new RuntimeException();
        }
    }
}
