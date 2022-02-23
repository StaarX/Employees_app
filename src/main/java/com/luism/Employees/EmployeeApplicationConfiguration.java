package com.luism.Employees;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeApplicationConfiguration {
    @Bean(name = "modelMapper")
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
