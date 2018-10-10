package com.senla.db.impl;

import com.senla.db.IBookDao;
import com.senla.db.IOrderDao;
import entities.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDao implements IOrderDao {

    private static final String DATE_OF_STARTED_ORDER = "dateOfStartedOrder";
    private static final String DATE_OF_COMPLETED_ORDER = "dateOfCompletedOrder";
    private static final String IS_COMPLETED_ORDER = "isCompletedOrder";
    private static final String BOOK_ID = "book_id";
    private static final String ID = "id";
    private IBookDao bookDao = new BookDao();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void add(Connection connection, Order order) throws SQLException {
        String sql = "INSERT INTO orders (dateOfStartedOrder, dateOfCompletedOrder, isCompletedOrder, book_id) VALUES (?,?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, dateFormat.format(order.getDateOfStartedOrder()));
            statement.setString(2, dateFormat.format(order.getDateOfCompletedOrder()));
            statement.setBoolean(3, order.getCompletedOrder());
            statement.setLong(4, order.getBook().getId());
            statement.executeUpdate();
        }
    }

    @Override
    public void deleteById(Connection connection, Long id) throws SQLException {
        String sql = "DELETE FROM orders WHERE id=" + id;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
        }
    }

    @Override
    public Order getById(Connection connection, Long id) throws SQLException {
        return getOrder(connection, "SELECT * FROM orders WHERE id=" + id);
    }

    @Override
    public void update(Connection connection, Order order) throws SQLException {
        String sql = "UPDATE orders SET dateOfStartedOrder=?, dateOfCompletedOrder=?, isCompletedOrder=?, book_id=? WHERE id=?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, dateFormat.format(order.getDateOfStartedOrder()));
            statement.setString(2, dateFormat.format(order.getDateOfCompletedOrder()));
            statement.setBoolean(3, order.getCompletedOrder());
            statement.setLong(4, order.getBook().getId());
            statement.setLong(5, order.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public List<Order> getAll(Connection connection) throws SQLException {
        return getOrders(connection, "SELECT * FROM orders");
    }

    @Override
    public void addAll(Connection connection, List<Order> orders) throws SQLException {
        for (Order order : orders) {
            add(connection, order);
        }
    }

    @Override
    public List<Order> getCompletedSortedByDate(Connection connection) throws SQLException {
        return getOrders(connection,"SELECT * FROM orders WHERE isCompletedOrder='1' ORDER BY dateOfCompletedOrder");
    }

    @Override
    public List<Order> getSortedByPrice(Connection connection) throws SQLException {
        return getOrders(connection,"SELECT o.id, o.dateOfStartedOrder, o.dateOfCompletedOrder, o.isCompletedOrder, b.price  FROM orders o join books b on o.book_id = b.id ORDER BY b.price");
    }

    @Override
    public List<Order> getSortedByState(Connection connection) throws SQLException {
        return getOrders(connection,"SELECT *  FROM orders ORDER BY isCompletedOrder");
    }

    @Override
    public List<Order> getCompleted(Connection connection) throws SQLException {
        return getOrders(connection,"SELECT * FROM orders WHERE isCompletedOrder='1';");
    }

    @Override
    public List<Order> getCompletedSortedByDateOfPeriod(Connection connection, Date startDate, Date endDate) throws SQLException {
        return getOrdersByPeriod(connection, startDate, endDate, "SELECT o.id, dateOfStartedOrder, dateOfCompletedOrder, b.price, isCompletedOrder, book_id FROM orders o JOIN books b on o.book_id = b.id WHERE isCompletedOrder='1' AND dateOfStartedOrder BETWEEN ? AND ? ORDER BY dateOfCompletedOrder;");
    }

    @Override
    public List<Order> getCompletedSortedByPriceOfPeriod(Connection connection, Date startDate, Date endDate) throws SQLException {
        return getOrdersByPeriod(connection, startDate, endDate, "SELECT o.id, dateOfStartedOrder, dateOfCompletedOrder, b.price, isCompletedOrder, book_id FROM orders o JOIN books b on o.book_id = b.id WHERE isCompletedOrder='1' AND dateOfStartedOrder BETWEEN ? AND ? ORDER BY b.price;");
    }

    @Override
    public Double getFullAmountByPeriod(Connection connection, Date startDate, Date endDate) throws SQLException {
        Double amount;
        String sql = "SELECT SUM(b.price) FROM orders o JOIN books b on o.book_id = b.id WHERE dateOfStartedOrder BETWEEN ? AND ?;";
        ResultSet result;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, dateFormat.format(startDate));
            statement.setString(2, dateFormat.format(endDate));
            result = statement.executeQuery();
            result.next();
            amount = result.getDouble(1);
        }
        return amount;
    }

    @Override
    public Integer getQuantityCompletedByPeriod(Connection connection, Date startDate, Date endDate) throws SQLException {
        Integer sum;
        String sql = "SELECT SUM(isCompletedOrder) FROM orders WHERE isCompletedOrder='1' AND dateOfCompletedOrder BETWEEN ? AND ?;";
        ResultSet result;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, dateFormat.format(startDate));
            statement.setString(2, dateFormat.format(endDate));
            result = statement.executeQuery();
            result.next();
            sum = result.getInt(1);
        }
        return sum;
    }

    private Order getOrder(Connection connection, String sql) throws SQLException {
        Order order = new Order();
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {
            if (result.next()) {
                order.setId(result.getLong(ID));
                order.setDateOfStartedOrder(result.getDate(DATE_OF_STARTED_ORDER));
                order.setDateOfCompletedOrder(result.getDate(DATE_OF_COMPLETED_ORDER));
                order.setCompletedOrder(result.getBoolean(IS_COMPLETED_ORDER));
                order.setBook(bookDao.getById(connection, result.getLong(BOOK_ID)));
                return order;
            }
        }
        return null;
    }

    private List<Order> getOrders(Connection connection, String sql) throws SQLException {
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                Order order = new Order();
                order.setId(result.getLong("id"));
                order.setDateOfStartedOrder(result.getDate("dateOfStartedOrder"));
                order.setDateOfCompletedOrder(result.getDate("dateOfCompletedOrder"));
                order.setCompletedOrder(result.getBoolean("isCompletedOrder"));
                order.setBook(bookDao.getById(connection, result.getLong("book_id")));
                orders.add(order);
            }
        }
        return orders;
    }

    private List<Order> getOrdersByPeriod(Connection connection, Date startDate, Date endDate, String sql) throws SQLException {
        List<Order> orders = new ArrayList<>();
        ResultSet result;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, dateFormat.format(startDate));
            statement.setString(2, dateFormat.format(endDate));
            result = statement.executeQuery();
            while (result.next()) {
                Order order = new Order();
                order.setId(result.getLong(ID));
                order.setCompletedOrder(result.getBoolean(IS_COMPLETED_ORDER));
                order.setDateOfCompletedOrder(result.getDate(DATE_OF_COMPLETED_ORDER));
                order.setDateOfStartedOrder(result.getDate(DATE_OF_STARTED_ORDER));
                order.setBook(bookDao.getById(connection, result.getLong(BOOK_ID)));
                orders.add(order);
            }
        }
        return orders;
    }
}
