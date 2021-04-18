package com.training.elnino;

import com.training.elnino.reader.ChunkableDataReaderImpl;
import com.training.elnino.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ElninoApplication implements CommandLineRunner {

    private final ChunkableDataReaderImpl dataReader;

    private final DataService service;

    public static final String FILE = "src/main/resources/tao-all2.dat";
    public static final String FILE_TEST = "src/main/resources/tao-all2-test.dat";

    public static void main(String[] args) {
        SpringApplication.run(ElninoApplication.class, args);
    }

    @Autowired
    public ElninoApplication(ChunkableDataReaderImpl dataReader, /*@Qualifier("batchDataServiceImpl")*/DataService service) {
        this.dataReader = dataReader;
        this.service = service;
    }


    @Override
    public void run(String... args) throws Exception {

        dataReader.read(FILE_TEST, 3, service::saveData);

    }
}
