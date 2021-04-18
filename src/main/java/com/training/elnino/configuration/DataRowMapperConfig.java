package com.training.elnino.configuration;

import com.training.elnino.mapper.DataRowMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class DataRowMapperConfig {

    @Bean
    @Scope("singleton")
    public DataRowMapper dataRowMapper() {
        return new DataRowMapper();
    }

}
