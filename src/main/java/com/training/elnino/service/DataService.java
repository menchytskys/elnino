package com.training.elnino.service;

import com.training.elnino.dao.DataRepository;
import com.training.elnino.model.DataRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DataService {

    @Autowired
    private DataRepository dataRepository;

    @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
    private int batchSize;


    public void saveDataRow(List<String> dataList) throws InterruptedException {
        LocalTime before = LocalTime.now();

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (String row : dataList) {
            executorService.execute(() -> {
                DataRow savedData = save(mapToDataRow(row));
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.HOURS);
        LocalTime after = LocalTime.now();
        Duration duration = Duration.between(before, after);
        System.out.println("persisted by = " + duration + " sec");

    }


    public void saveDataRowBatch(List<String> dataList) throws InterruptedException {
        LocalTime before = LocalTime.now();
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int start = 0; start < dataList.size(); start += batchSize) {
            int end = Math.min(start + batchSize, dataList.size());
            List<String> batchList = dataList.subList(start, end);

            List<DataRow> dataRows = new ArrayList<>();
            for (String s : batchList) {
                DataRow dataRow = mapToDataRow(s);
                dataRows.add(dataRow);
            }

            executorService.execute(() -> {
                saveAll(dataRows);
                dataRows.clear();
            });

        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.HOURS);
        LocalTime after = LocalTime.now();
        Duration duration = Duration.between(before, after);
        System.out.println("persisted by batch in = " + duration + " sec");
    }

    @Transactional
    public DataRow save(DataRow dataRow) {

        return dataRepository.save(dataRow);
    }

    @Transactional
    public List<DataRow> saveAll(List<DataRow> dataRows) {

        return dataRepository.saveAll(dataRows);
    }

    private DataRow mapToDataRow(String row) {
        DataRow dataRow = new DataRow();

        String[] data = row.split(" ");
        List<String> collect = Stream.of(data).map(s -> s.equals(".") ? "0" : s).collect(Collectors.toList());

        dataRow.setObs(Long.parseLong(collect.get(0)));
        dataRow.setYear(Integer.parseInt(collect.get(1)));
        dataRow.setMonth(Integer.parseInt(collect.get(2)));
        dataRow.setDay(Integer.parseInt(collect.get(3)));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yMMdd");
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
