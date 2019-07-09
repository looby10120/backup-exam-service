package com.digitalacademy.examservice.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class ExamServiceInterceptorAppConfig implements WebMvcConfigurer {

    @Autowired
    com.digitalacademy.examservice.interceptors.ExamServiceInterceptor examServiceInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(examServiceInterceptor);
    }
}
