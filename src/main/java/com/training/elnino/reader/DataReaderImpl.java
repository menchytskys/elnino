package com.training.elnino.reader;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataReaderImpl implements DataReader {

    public static final String FILE = "src/main/resources/tao-all2.dat";
    public static final String FILE_TEST = "src/main/resources/tao-all2-test.dat";

    @Override
    public List<String> read() {

//        LocalTime before = LocalTime.now();
        List<String> list = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(FILE_TEST);
             InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(isr)
        ) {
            String str;
            while ((str = reader.readLine()) != null) {
                list.add(str);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
//        LocalTime after = LocalTime.now();
//        Duration duration = Duration.between(before, after);
//        System.out.println("BufferedReader result = " + duration);
//        System.out.println("list size " + list.size());

        return list;
    }
}
