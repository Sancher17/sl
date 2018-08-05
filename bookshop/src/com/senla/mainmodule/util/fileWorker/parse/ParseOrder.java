package com.senla.mainmodule.util.fileWorker.parse;


import com.senla.mainmodule.entities.Book;
import com.senla.mainmodule.entities.Order;
import com.senla.mainmodule.repositories.IRepositoryBook;
import com.senla.mainmodule.repositories.impl.RepositoryBook;


public class ParseOrder extends Parse {

    private Order[] tempData = new Order[1];

    private IRepositoryBook repositoryBook = RepositoryBook.getInstance();

    public ParseOrder(String filePath) {
        super(filePath);
    }

    @Override
    public Order createObject(String str)  {
        String[] temp = str.split("/");
        String checkNull = " null";
        if (!temp[0].equals(checkNull)) {
            String id = temp[0].replaceAll("\\s+", "");
            String dateOfStartedOrder = temp[1];
            String nameBook = temp[2].replaceAll("\\s+", "");
            String isCompletedOrder = temp[3];
            String dateOfCompletedOrder = temp[5];
            for (int i = 0; i < 1; i++) {
                Book book = repositoryBook.getBookByName(nameBook);
                Order parseOrder = new Order(book);
                parseOrder.setId(parseLong(id));
                parseOrder.setDateOfStartedOrder(parseDate(dateOfStartedOrder));
                parseOrder.setBook(book);
                parseOrder.setCompletedOrder(parseBoolean(isCompletedOrder));
                if (dateOfCompletedOrder.equals("null")){
                    parseOrder.setDateOfCompletedOrder(null);
                }else {
                    parseOrder.setDateOfCompletedOrder(parseDate(dateOfCompletedOrder));
                }
                tempData[i] = parseOrder;
            }
            return tempData[0];
        }
        return null;
    }
}
