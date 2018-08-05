package services;

import entities.Order;
import repositories.IRepositoryOrder;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public interface IServiceOrder {

    void addOrder(Long bookId);

    void addOrder(Date startOrder, Long bookId);

    void deleteOrderById(Long id);

    List<Order> getAll();

    void setCompleteOrderById(Long id);

    void setCompleteOrderById(Long id, Date dateOfCompleted);

    IRepositoryOrder getRepositoryOrder();

    String getCompletedOrders();

    String getCompletedOrdersSortedByDateOfPeriod(Date startDate, Date endDate);

    String getCompletedOrdersSortedByPriceOfPeriod(Date startDate, Date endDate);

    void sortCompletedOrdersByDate();

    void sortOrdersByPrice();

    void sortOrdersByState();

    String getOrdersFullAmountByPeriod(Date startDate, Date endDate);

    String getQuantityCompletedOrdersByPeriod(Date startDate, Date endDate);

    String getOrderById(Long id);
}
