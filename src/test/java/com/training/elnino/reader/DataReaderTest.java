package com.training.elnino.reader;

import com.training.elnino.exception.DataReaderInputSourceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class DataReaderTest {

    public static final String FILE_TEST = "src/main/resources/tao-all2-test.dat";
    ChunkableDataReader<String, String> dataReader;

    @Autowired
    public DataReaderTest(ChunkableDataReader<String, String> dataReader) {
        this.dataReader = dataReader;
    }

    @Test
    public void ShouldReadDataWhenReadFile() throws DataReaderInputSourceException {
        List<String> result = new ArrayList<>();
        dataReader.read(FILE_TEST, 3, result::addAll);

        Assertions.assertEquals(10, result.size());
    }

    @Test()
    public void ShouldThrowExceptionWhenSourceNull() {
        Exception exception = assertThrows(DataReaderInputSourceException.class, () -> dataReader.read("null", 0, System.out::println));

        String expectedMessage = "Source is null or chunkSize = 0";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
