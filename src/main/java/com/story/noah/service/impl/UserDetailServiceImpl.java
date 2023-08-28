package com.story.noah.service.impl;


import com.story.noah.config.UserSecurity;
import com.story.noah.repository.jpa.UserRepository;
import com.story.noah.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
                return userRepository.findByEmail(email).map(UserSecurity::new)
                        .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
            }
        };
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return userRepository.findByEmail(username).map(UserSecurity::new)
//                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
//    }
}
