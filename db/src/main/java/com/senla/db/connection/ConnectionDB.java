package com.senla.db.connection;

import com.senla.di.DependencyInjection;
import com.senla.propertiesmodule.IPropertyHolder;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.senla.mainmodule.constants.Constants.*;

public class ConnectionDB {

    private static final Logger log = Logger.getLogger(ConnectionDB.class);

    private static final String NO_CONNECTION_TO_DB = "Не удачное подключение к БД ";
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    private static IPropertyHolder propertyHolder = DependencyInjection.getBean(IPropertyHolder.class);
    private static Connection connection;

    private ConnectionDB() {
    }

    public static Connection getConnection() {
       if (connection == null){
           try {
               Class.forName(JDBC_DRIVER);
               propertyHolder.dbConnection();
               connection = DriverManager.getConnection(URL, USER, PASSWORD);
           } catch (SQLException | ClassNotFoundException e) {
               log.error(NO_CONNECTION_TO_DB + e);
           }
       }
       return connection;
    }
}
