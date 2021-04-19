package com.training.elnino.mapper;

import com.training.elnino.model.DataRow;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SpringBootTest()
public class DataRowMapperTest {

    @Test
    public void shouldReturnDataRowWhenTakeString() {
        //given
        String inputString = "1 80 3 7 800307 -0.02 -109.46 -6.8 0.7 . 26.14 26.24";
        DataRowMapper dataRowMapper = new DataRowMapper();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yMMdd");
        LocalDate expectedDate = LocalDate.parse("800307", formatter);
        //when
        DataRow resultDataRow = dataRowMapper.mapToDataRow(inputString);
        //then
        Assertions.assertEquals(1, resultDataRow.getObs());
        Assertions.assertEquals(80, resultDataRow.getYear());
        Assertions.assertEquals(3, resultDataRow.getMonth());
        Assertions.assertEquals(7, resultDataRow.getDay());
        Assertions.assertEquals(expectedDate, resultDataRow.getDate());
        Assertions.assertEquals(-0.02, resultDataRow.getLatitude());
        Assertions.assertEquals(-109.46, resultDataRow.getLongitude());
        Assertions.assertEquals(-6.8, resultDataRow.getZonWinds());
        Assertions.assertEquals(0.7, resultDataRow.getMerWinds());
        Assertions.assertEquals(0, resultDataRow.getHumidity());
        Assertions.assertEquals(26.14, resultDataRow.getAirTemp());
        Assertions.assertEquals(26.24, resultDataRow.getsSeaSurfaceTemp());
    }
}
