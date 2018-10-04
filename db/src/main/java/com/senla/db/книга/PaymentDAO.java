package com.senla.db.книга;

import java.sql.Connection;
import java.util.List;

public class PaymentDAO extends AbstractDAO <Integer, Payment>  {


    public PaymentDAO(Connection connection) {
        super(connection);
    }

    public void findEntityById(int id) {

    }

    public void delete(int id) {

    }

    @Override
    public List<Payment> findAll() {
        return null;
    }

    @Override
    public Payment findEntityById(Integer id) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean delete(Payment entity) {
        return false;
    }

    @Override
    public boolean create(Payment entity) {
        return false;
    }

    @Override
    public Payment update(Payment entity) {
        return null;
    }
}
