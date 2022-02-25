package com.company.blog.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration<T> {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


}
