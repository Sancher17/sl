package com.senla.db.книга.dbhelper;

import com.senla.db.книга.Abonent;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class PreparedJDBCRunner {

    public static void main(String[] args) {

        ArrayList list = new ArrayList() {
            {
                add(new Abonent(87, 1658468, "Кожух Дмитрий"));
                add(new Abonent(51, 8866711, "Буйкевич Александр"));
            }
        };

        // данный класс ускорят подготовку запроса
        DbHelper helper = null;

        PreparedStatement statement = null;

        try {
            helper = new DbHelper();
            statement = helper.getPreparedStatement();
            for (Object abonent : list) {
                helper.insertOrder(statement, (Abonent) abonent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            helper.closeStatement(statement);
        }

    }
}
