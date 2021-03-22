package com.training.elnino.service;

import com.training.elnino.dao.DataRepository;
import com.training.elnino.maper.DataRowMapper;
import com.training.elnino.model.DataRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class DataService {

    private DataRepository dataRepository;

    private DataRowMapper dataRowMapper;

    @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
    private int batchSize;

    @Autowired
    public void setDataRepository(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @Autowired
    public void setDataRowMapper(DataRowMapper dataRowMapper) {
        this.dataRowMapper = dataRowMapper;
    }

    @Transactional
    public void saveDataRow(List<String> dataList) {
        for (String row : dataList) {
            dataRepository.save(dataRowMapper.mapToDataRow(row));
        }
    }

    public void saveDataRowBatch(List<String> dataList) throws InterruptedException {
        LocalTime before = LocalTime.now();
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int start = 0; start < dataList.size(); start += batchSize) {
            int end = Math.min(start + batchSize, dataList.size());
            List<String> batchList = dataList.subList(start, end);

            List<DataRow> dataRows = new ArrayList<>();
            for (String s : batchList) {
                DataRow dataRow = dataRowMapper.mapToDataRow(s);
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
    public List<DataRow> saveAll(List<DataRow> dataRows) {

        return dataRepository.saveAll(dataRows);
    }
}
