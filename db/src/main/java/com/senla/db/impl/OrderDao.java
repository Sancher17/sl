package com.senla.db.impl;

import com.senla.db.IBookDao;
import com.senla.db.IOrderDao;
import com.senla.db.connection.ConnectionDB;
import com.senla.di.DependencyInjection;
import entities.Order;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class OrderDao implements IOrderDao {

    private static final Logger log = Logger.getLogger(OrderDao.class);
    private Connection connection;
    private IBookDao bookDao = new BookDao();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public OrderDao() {
        connection = ConnectionDB.getConnection();
    }

    @Override
    public void add(Order order) {
        String sql = "INSERT INTO orders (dateOfStartedOrder, dateOfCompletedOrder, isCompletedOrder, book_id) VALUES (?,?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, sdf.format(order.getDateOfStartedOrder()));
            statement.setString(2, sdf.format(order.getDateOfCompletedOrder()));
            statement.setBoolean(3, order.getCompletedOrder());
            statement.setLong(4, order.getBook().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("Не удачная попытка добавить Order в БД - " + e);
        }
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM orders WHERE id=" + id;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("Не удачная попытка удалить Order из БД - " + e);
        }
    }

    @Override
    public Order getById(Long id) {
        String sql = "SELECT * FROM orders WHERE id=" + id;
        Order order = new Order();
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {
           if (result.next()){
               order.setId(result.getLong("id"));
               order.setDateOfStartedOrder(result.getDate("dateOfStartedOrder"));
               order.setDateOfCompletedOrder(result.getDate("dateOfCompletedOrder"));
               order.setCompletedOrder(result.getBoolean("isCompletedOrder"));
               order.setBook(bookDao.getById(result.getLong("book_id")));
               return order;
           }
        } catch (SQLException e) {
            log.error("Не удачная попытка получить Order из БД - " + e);
        }
        return null;
    }

    @Override
    public void update(Order order) {
//        String sql = "UPDATE books SET id = ?, dateAddedBookToStore = ?, dateOfPublication = ?, description = ? , isAvailable = ?, isOld = ?, nameBook = ?, price = ?";
//        try (PreparedStatement statement = connection.prepareStatement(sql)){
//            statement.setDouble(1, book.getId());
//            statement.setString(2, sdf.format(book.getDateAddedBookToStore()));
//            statement.setString(3, sdf.format(book.getDateOfPublication()));
//            statement.setString(4, book.getDescription());
//            statement.setBoolean(5, book.getAvailable());
//            statement.setBoolean(6, book.getOld());
//            statement.setString(7, book.getNameBook());
//            statement.setDouble(8, book.getPrice());
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                Order order = new Order();
                order.setId(result.getLong("id"));
                order.setDateOfStartedOrder(result.getDate("dateOfStartedOrder"));
                order.setDateOfCompletedOrder(result.getDate("dateOfCompletedOrder"));
                order.setCompletedOrder(result.getBoolean("isCompletedOrder"));
                order.setBook(bookDao.getById(result.getLong("book_id")));
                orders.add(order);
            }
        } catch (SQLException e) {
            log.error("Не удачная попытка получить все Orders из БД - " + e);
        }
        return orders;
    }

    @Override
    public void addAll(List<Order> entity) {

    }
}
