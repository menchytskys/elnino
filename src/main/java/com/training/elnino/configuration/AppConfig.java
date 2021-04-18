package com.training.elnino.configuration;

import com.training.elnino.dao.DataRepository;
import com.training.elnino.mapper.DataRowMapper;
import com.training.elnino.service.BatchDataServiceImpl;
import com.training.elnino.service.DataService;
import com.training.elnino.service.DefaultDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigurationPackage
@ComponentScan(basePackages = {"com.training"})
public class AppConfig {

    @Value("${serviceName}")
    private String serviceName;

    private final DataRepository dataRepository;
    private final DataRowMapper dataRowMapper;

    @Autowired

    public AppConfig(DataRepository dataRepository, DataRowMapper dataRowMapper) {
        this.dataRepository = dataRepository;
        this.dataRowMapper = dataRowMapper;
    }

    @Bean
    public DataService dataService() {
        switch (serviceName) {
            case "DefaultDataServiceImpl":
                return new DefaultDataServiceImpl(dataRepository, dataRowMapper);
            case "BatchDataServiceImpl":
                return new BatchDataServiceImpl(dataRepository, dataRowMapper);
            default:
                return new DefaultDataServiceImpl(dataRepository, dataRowMapper);
        }

    }
}
