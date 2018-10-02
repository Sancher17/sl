package com.senla.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HelperJDBC {

    private static String dbName = "bookshop";
    private static String URL = "jdbc:mysql://localhost:3306/"+dbName+"?autoReconnect=true&useSSL=false";
    private static String user = "root";
    private static String pass = "1512";

    private static Connection connection;


    private HelperJDBC() {
    }

    public static Connection getConnection() {

       if (connection == null){
           try {
               Class.forName("com.mysql.jdbc.Driver");
               connection = DriverManager.getConnection(URL, user, pass);
               System.out.println("Connection OK");
           } catch (SQLException | ClassNotFoundException e) {
               e.printStackTrace();
               System.out.println("Connection Error");
           }
       }
       return connection;
    }
}
