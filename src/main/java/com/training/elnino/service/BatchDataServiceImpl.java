package com.training.elnino.service;

import com.training.elnino.dao.DataRepository;
import com.training.elnino.mapper.DataRowMapper;
import com.training.elnino.model.DataRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

public class BatchDataServiceImpl implements DataService {

    private final DataRepository dataRepository;
    private final DataRowMapper dataRowMapper;

    public BatchDataServiceImpl(DataRepository dataRepository, DataRowMapper dataRowMapper) {
        this.dataRepository = dataRepository;
        this.dataRowMapper = dataRowMapper;
    }

    @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
    private int batchSize;

    @Override
    public void saveData(List<String> dataList) {
        for (int start = 0; start < dataList.size(); start += batchSize) {
            int end = Math.min(start + batchSize, dataList.size());
            List<String> batchList = dataList.subList(start, end);

            List<DataRow> dataRows = new ArrayList<>();
            for (String s : batchList) {
                DataRow dataRow = dataRowMapper.mapToDataRow(s);
                dataRows.add(dataRow);
            }
            dataRepository.saveAll(dataRows);
            dataRows.clear();
        }
    }
}
