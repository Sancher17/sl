package com.senla.services.impl;

import com.senla.db.connection.ConnectionDB;
import com.senla.services.IServiceExit;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class ServiceExit implements IServiceExit {

    private static final Logger log = Logger.getLogger(ServiceExit.class);
    private static final String CONNECTION_CLOSE_ERROR = "Ошибка при закрытии Connection";

    @Override
    public void closeConnection() {
        try {
            Connection connection = ConnectionDB.getConnection();
            connection.close();
        } catch (SQLException e) {
            log.error(CONNECTION_CLOSE_ERROR);
        }
    }
}
