package com.training.elnino.service;

import com.training.elnino.dao.DataRepository;
import com.training.elnino.mapper.DataRowMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DefaultDataServiceImpl implements DataService {

    private final DataRepository dataRepository;

    private final DataRowMapper dataRowMapper;

    public DefaultDataServiceImpl(DataRepository dataRepository, DataRowMapper dataRowMapper) {
        this.dataRepository = dataRepository;
        this.dataRowMapper = dataRowMapper;
    }


    @Override
    public void saveData(List<String> dataList) {
        for (String row : dataList) {
            dataRepository.save(dataRowMapper.mapToDataRow(row));
        }
    }
}
