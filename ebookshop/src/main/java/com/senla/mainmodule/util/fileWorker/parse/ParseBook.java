package com.senla.mainmodule.util.fileWorker.parse;

import com.senla.mainmodule.entities.Book;

public class ParseBook extends Parse {

    private Book[] tempData = new Book[1];

    public ParseBook(String filePath) {
        super(filePath);
    }

    @Override
    public Book createObject(String str) {
        String[] temp = str.split("/");
        String checkNull = " null";
        if (!temp[0].equals(checkNull)){
            String id = temp[0].replaceAll("\\s+","");
            String name = temp[1].replaceAll("\\s+", "");
            String dateOfPublication = temp[2];
            String price = temp[3];
            String isAvailable = temp[4];
            String dateAddedBookToStore = temp[5];
            String description = temp[6];

            for (int i = 0; i < 1; i++) {
                Book parserBook = new Book();
                parserBook.setId(parseLong(id));
                parserBook.setNameBook(name);
                parserBook.setDateOfPublication(parseDate(dateOfPublication));
                parserBook.setDateAddedBookToStore(parseDate(dateAddedBookToStore));
                parserBook.setPrice(parseDouble(price));
                parserBook.setAvailable(parseBoolean(isAvailable));
                parserBook.setDescription(description);

                tempData[i] = parserBook;
            }
            return tempData[0];
        }
        return null;
    }
}
