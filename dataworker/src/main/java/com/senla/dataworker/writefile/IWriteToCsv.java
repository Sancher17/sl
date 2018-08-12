package com.senla.dataworker.writefile;

import java.util.List;

public interface IWriteToCsv<T> {

    void writeToCsv(List<T> list, String path);

}
