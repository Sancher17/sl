package com.senla.db.книга;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AbonentDAO extends AbstractDAO<Integer, Abonent> {

    private static String SQL_SELECT_ALL_ABONENTS = "SELECT * FROM phonebook";
    private static String SQL_SELECT_ABONENT_BY_LASTNAME = "SELECT idphonebook,phone FROM phonebook WHERE lastname=?";

    public AbonentDAO(Connection connection) {
        super(connection);
    }

    public List<Abonent> findAll() {
        List<Abonent> abonents = new ArrayList<>();
        Statement st = null;
        try {
            st = connector.getStatement();
            ResultSet resultSet = st.executeQuery(SQL_SELECT_ALL_ABONENTS);
            while (resultSet.next()) {
                Abonent abonent = new Abonent();
                abonent.setId(resultSet.getInt("idphonebook"));
                abonent.setPhone(resultSet.getInt("phone"));
                abonent.setLastName(resultSet.getString("lastname"));
                abonents.add(abonent);
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            this.closeStatement(st);
        }
        return abonents;
    }

    @Override
    public Abonent findEntityById(Integer id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(Integer id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean create(Abonent entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Abonent update(Abonent entity) {
        throw new UnsupportedOperationException();
    }

    // собственный метод GenericDAO
    public Abonent findAbonentByLastName(String name) {
        Abonent abonent = new Abonent();
        Connection cn = null;
        PreparedStatement st = null;
        try {
            cn = ConnectionPool.getConnection();
            st = cn.prepareStatement(SQL_SELECT_ABONENT_BY_LASTNAME);
            st.setString(1, name);
            ResultSet resultSet = st.executeQuery();
            resultSet.next();
            abonent.setId(resultSet.getInt("idphonebook"));
            abonent.setPhone(resultSet.getInt("phone"));
            abonent.setLastName(name);
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            closeStatement(st);
        }
        return abonent;
    }

    @Override
    public boolean delete(Abonent entity) {
        throw new UnsupportedOperationException();
    }
}

