package com.training.elnino;

import com.training.elnino.reader.ChunkableDataReaderImpl;
import com.training.elnino.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ElninoApplication implements CommandLineRunner {

	private ChunkableDataReaderImpl dataReader;

	private DataService service;

	public static final String FILE = "src/main/resources/tao-all2.dat";
	public static final String FILE_TEST = "src/main/resources/tao-all2-test.dat";

	public static void main(String[] args) {
		SpringApplication.run(ElninoApplication.class, args);
	}

	@Autowired
	public void setDataReader(ChunkableDataReaderImpl dataReader) {
		this.dataReader = dataReader;
	}

	@Autowired
	public void setService(DataService service) {
		this.service = service;
	}

	@Override
	public void run(String... args) throws Exception {
//		List<String> data = dataReader.read(FILE_TEST);

		dataReader.read(FILE_TEST, 3, (List<String> data) -> service.saveDataRow(data));

//		service.saveDataRow(data);

//		service.saveDataRowBatch(data);
	}
}
