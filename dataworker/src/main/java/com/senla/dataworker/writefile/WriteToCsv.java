package com.senla.dataworker.writefile;

import java.util.List;

public interface WriteToCsv<T> {

    void write(List<T> list);

}
