package com.training.elnino.reader;

import java.util.List;
import java.util.function.Consumer;

public interface ChunkableDataReader<T> {

    public void read(T source, int chunkSize, Consumer<List<T>> consumer);

}
