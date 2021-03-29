package com.training.elnino.mapper;

import com.training.elnino.configuration.DataRowMapperConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class DataRowMapperTest {

    private static final String DATA_FORMAT = "dd";

    @Test
    public void givenSingletonScope_whenMapData_thenEqual() {
        AbstractApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(DataRowMapperConfig.class);

        DataRowMapper dataRowMapperA = (DataRowMapper) applicationContext.getBean("dataRowMapper");
        DataRowMapper dataRowMapperB = (DataRowMapper) applicationContext.getBean("dataRowMapper");


        dataRowMapperA.setDateFormat("dd");
        assertEquals(DATA_FORMAT, dataRowMapperB.getDateFormat());

        applicationContext.close();
    }

}
