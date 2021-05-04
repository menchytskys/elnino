package com.training.elnino;

import com.training.elnino.reader.ChunkableDataReader;
import com.training.elnino.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    private static DataService dataService;
    private static ChunkableDataReader<String, String> chunkableDataReader;

    @Autowired
    public Main(DataService dataService, ChunkableDataReader<String, String> chunkableDataReader) {
        Main.dataService = dataService;
        Main.chunkableDataReader = chunkableDataReader;
    }

    public static void main(String[] args) {
        String inputFilePath = args[0];

        chunkableDataReader.read(inputFilePath, 2, list -> dataService.saveData(list));
    }
}
