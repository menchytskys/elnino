package com.training.elnino;

import com.training.elnino.reader.DataReaderImpl;
import com.training.elnino.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ElninoApplication implements CommandLineRunner {

	@Autowired
	DataReaderImpl dataReader;

	@Autowired
	DataService service;

	public static void main(String[] args) {
		SpringApplication.run(ElninoApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		List<String> data = dataReader.read();

//		service.saveDataRow(data);

		service.saveDataRowBatch(data);
	}
}
