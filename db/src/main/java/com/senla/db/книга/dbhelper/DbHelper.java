package com.senla.db.книга.dbhelper;

import com.senla.db.connection.ConnectionDB;
import com.senla.db.книга.Abonent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbHelper {

    private final static String SQL_INSERT = "INSERT INTO phonebook(idphonebook, lastname, phone ) VALUES(?,?,?)";
    private Connection connect;

    public DbHelper() throws SQLException {
        connect = ConnectionDB.getConnection();
    }

    public PreparedStatement getPreparedStatement() {
        PreparedStatement ps = null;
        try {
            ps = connect.prepareStatement(SQL_INSERT);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }

    public boolean insertOrder(PreparedStatement ps, Abonent ab) {

        try {
            ps.setInt(1, ab.getId());
            ps.setString(2, ab.getLastname());
            ps.setInt(3, ab.getPhone());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void closeStatement(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
