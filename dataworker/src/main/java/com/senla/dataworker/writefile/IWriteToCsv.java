package com.senla.dataworker.writefile;

import java.util.List;

public interface IWriteToCsv<T> {

    void write(List<T> list);

}
