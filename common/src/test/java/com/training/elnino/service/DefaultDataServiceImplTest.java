package com.training.elnino.service;

import com.training.elnino.BaseIntegrationTest;
import com.training.elnino.model.DataRow;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;


public class DefaultDataServiceImplTest extends BaseIntegrationTest {

    @Autowired
    DataService dataService;

    @Test
    public void shouldReturnAllDataWhenSaveData() {
        //given
        List<String> dataRows = Arrays.asList("1 80 3 7 800307 -0.02 -109.46 -6.8 0.7 . 26.14 26.24",
                "2 80 3 8 800308 -0.02 -109.46 -4.9 1.1 . 25.66 25.97",
                "3 80 3 9 800309 -0.02 -109.46 -4.5 2.2 . 25.69 25.28");
        //when
        dataService.saveData(dataRows);
        //then
        List<DataRow> allDataRows = dataService.getAllDataRows();
        Assertions.assertEquals(3, allDataRows.size());
        Assertions.assertEquals(25.66, allDataRows.get(1).getAirTemp());
    }

}
