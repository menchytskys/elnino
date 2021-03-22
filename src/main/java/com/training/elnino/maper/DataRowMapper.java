package com.training.elnino.maper;

import com.training.elnino.model.DataRow;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class DataRowMapper {

    private static final String SPLITTER = " ";
    private static final String DATE_FORMAT = "yMMdd";

    public DataRow mapToDataRow(String row) {
        DataRow dataRow = new DataRow();

        String[] data = row.split(SPLITTER);
        List<String> collect = Stream.of(data).map(s -> s.equals(".") ? "0" : s).collect(Collectors.toList());

        dataRow.setObs(Long.parseLong(collect.get(0)));
        dataRow.setYear(Integer.parseInt(collect.get(1)));
        dataRow.setMonth(Integer.parseInt(collect.get(2)));
        dataRow.setDay(Integer.parseInt(collect.get(3)));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        LocalDate dateTime = LocalDate.parse(collect.get(4), formatter);
        dataRow.setDate(dateTime);
        dataRow.setLatitude(Double.parseDouble(collect.get(5)));
        dataRow.setLongitude(Double.parseDouble(collect.get(6)));
        dataRow.setZonWinds(Double.parseDouble(collect.get(7)));
        dataRow.setMerWinds(Double.parseDouble(collect.get(8)));
        dataRow.setHumidity(Double.parseDouble(collect.get(9)));
        dataRow.setAirTemp(Double.parseDouble(collect.get(10)));
        dataRow.setsSeaSurfaceTemp(Double.parseDouble(collect.get(11)));

        return dataRow;
    }

}
