package com.training.elnino.Controller;

import com.training.elnino.model.DataRow;
import com.training.elnino.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DataController {

    private final DataService dataService;

    @GetMapping("/data")
    public List<DataRow> getAllData() {

        return dataService.getAllDataRows();
    }

    @Autowired
    public DataController(DataService dataService) {
        this.dataService = dataService;
    }
}
