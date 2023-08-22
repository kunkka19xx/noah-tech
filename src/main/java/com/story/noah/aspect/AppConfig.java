package com.story.noah.aspect;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public PageableHolder pageableHolder() {
        return new PageableHolder();
    }
}
