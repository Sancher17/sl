package Zanyatie4.Task1.data;

import Zanyatie4.Task1.entity.Book;
import Zanyatie4.Task1.entity.Order;
import Zanyatie4.Task1.service.OrderService;

public class ParseOrder extends Parse {

    private Order[] tempData = new Order[1];

    private OrderService orderService;

    public ParseOrder(String filePath, OrderService orderService) {
        super(filePath);
        this.orderService = orderService;
    }

    @Override
    public Order createObject(String str) {
        String[] temp = str.split("/");
        String checkNull = " null";
        if (!temp[0].equals(checkNull)) {
            String dateOfStartedOrder = temp[0];
            String nameBook = temp[1].replaceAll("\\s+", "");
            String isCompletedOrder = temp[2];
            String dateOfCompletedOrder = temp[4];
            for (int i = 0; i < 1; i++) {
                Book book = orderService.getBooks().getBookByName(nameBook);
                tempData[i] = new Order(parseDate(dateOfStartedOrder), book, parseBoolean(isCompletedOrder), parseDate(dateOfCompletedOrder));
            }
            return tempData[0];
        }
        return null;
    }
}
