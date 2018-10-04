package com.senla.db.connection;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

    private static final Logger log = Logger.getLogger(ConnectionDB.class);

    private static String dbName = "bookshop";
    private static String URL = "jdbc:mysql://localhost:3306/"+dbName+"?autoReconnect=true&useSSL=false";
    private static String user = "root";
    private static String pass = "1512";

    private static Connection connection;


    private ConnectionDB() {
    }

    public static Connection getConnection() {

       if (connection == null){
           try {
               Class.forName("com.mysql.jdbc.Driver");
               connection = DriverManager.getConnection(URL, user, pass);
           } catch (SQLException | ClassNotFoundException e) {
               log.error("Не удачное подключение к БД " + e);
           }
       }
       return connection;
    }
}
