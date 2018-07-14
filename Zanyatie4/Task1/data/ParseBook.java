package Zanyatie4.Task1.data;

import Zanyatie4.Task1.entity.Book;

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
            String name = temp[0].replaceAll("\\s+", "");
            String dateOfPublication = temp[1];
            String price = temp[2];
            String isAvailable = temp[3];
            String dateAddedBookToStore = temp[4];
            String description = temp[5];

            for (int i = 0; i < 1; i++) {
                tempData[i] = new Book(name,
                        parseDate(dateOfPublication),
                        parseDate(dateAddedBookToStore),
                        parseDouble(price),
                        parseBoolean(isAvailable),
                        description);
            }
            return tempData[0];
        }
        return null;
    }
}
