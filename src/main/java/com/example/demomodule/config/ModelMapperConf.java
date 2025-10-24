package com.example.demomodule.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConf {
    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper;
        modelMapper = new ModelMapper();
        return modelMapper;
    }
}
