package com.training.elnino.reader;

import java.util.List;
import java.util.function.Consumer;

public interface ChunkableDataReader<S, C> {

    void read(S source, int chunkSize, Consumer<List<C>> consumer);

}
