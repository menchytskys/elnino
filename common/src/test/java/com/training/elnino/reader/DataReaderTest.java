package com.training.elnino.reader;

import com.training.elnino.exception.DataReaderInputSourceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

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
        //get
        List<String> result = new ArrayList<>();
        //when
        dataReader.read(FILE_TEST, 3, result::addAll);
        //then
        Assertions.assertEquals(10, result.size());
        Assertions.assertEquals("2 80 3 8 800308 -0.02 -109.46 -4.9 1.1 . 25.66 25.97", result.get(1));
    }

    @Test()
    public void ShouldThrowExceptionWhenSourceNull() {
        //when
        Exception exception = assertThrows(DataReaderInputSourceException.class, () -> dataReader.read("null", 0, System.out::println));
        //then
        String expectedMessage = "Source is null or chunkSize = 0";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
