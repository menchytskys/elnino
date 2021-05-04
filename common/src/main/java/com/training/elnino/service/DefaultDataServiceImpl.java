package com.training.elnino.service;

import com.training.elnino.dao.DataRepository;
import com.training.elnino.mapper.DataRowMapper;
import com.training.elnino.model.DataRow;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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

    @Override
    public List<DataRow> getAllDataRows() {
        return dataRepository.findAll();
    }
}
