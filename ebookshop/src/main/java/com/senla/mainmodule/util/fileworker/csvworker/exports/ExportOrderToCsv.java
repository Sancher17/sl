package com.senla.mainmodule.util.fileworker.csvworker.exports;

import com.senla.mainmodule.entities.Order;
import com.senla.mainmodule.services.IService;
import com.senla.mainmodule.services.IServiceOrder;
import com.senla.mainmodule.services.impl.ServiceOrder;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static com.senla.mainmodule.constants.Constants.*;

public class ExportOrderToCsv implements IExportCsv {

    public void exportToFile(String path) {
        List<Order> tempOrder = readFromRepository();
        DateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        StringBuilder sb = new StringBuilder();
        for (Order order : tempOrder) {
            sb.append(order.getId()).append(";");
            sb.append(sdf.format(order.getDateOfStartedOrder())).append(";");
            sb.append(order.getBook().getNameBook()).append(";");
            sb.append(order.getCompletedOrder()).append(";");
            sb.append(order.getBook().getPrice()).append(";");
            if (order.getDateOfCompletedOrder() == null){
                sb.append("null");
            }else {
                sb.append(sdf.format(order.getDateOfCompletedOrder())).append(";");
            }
            sb.append("\n");
        }
        try (PrintWriter pw = new PrintWriter(new File(path))) {
            pw.write(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    private List<Order> readFromRepository(){
        IService service = ServiceOrder.getInstance();
        return service.getRepo();
    }
}















