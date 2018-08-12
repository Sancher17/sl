package com.senla.dataworker.writefile;

import java.util.List;

import static com.senla.dataworker.constants.ConstantsDataWorker.PATH_BOOK_CSV;

public class WriteBookToCsv extends WriteToCsv {


    @Override
    public void writeToCsv(List list, String path) {
            writeToCsv(list, PATH_BOOK_CSV);
    }
}
