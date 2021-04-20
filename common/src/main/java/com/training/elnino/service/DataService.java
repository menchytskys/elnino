package com.training.elnino.service;

import com.training.elnino.model.DataRow;

import java.util.List;

public interface DataService {

    void saveData(List<String> dataList);

    List<DataRow> getAllDataRows();

}
