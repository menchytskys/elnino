package com.training.elnino.reader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Component
public class ChunkableDataReaderImpl implements ChunkableDataReader<String> {

    private static final Logger LOG = LogManager.getLogger(ChunkableDataReaderImpl.class);

    @Override
    public void read(String source, int chunkSize, Consumer<List<String>> consumer) {
        List<String> list = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(source);
             InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(isr)
        ) {
            String str;
            while ((str = reader.readLine()) != null) {
                if (list.size() == chunkSize) {
                    consumer.accept(list);
                    list.clear();
                } else {
                    list.add(str);
                }
            }
            if (!list.isEmpty()) {
                consumer.accept(list);
                list.clear();
            }
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

}
