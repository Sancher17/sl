package com.senla.mainmodule.util.fileworker.csvworker.exports;

import com.senla.mainmodule.services.IService;
import entities.Order;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class ExportOrderToCsv extends Export {

    private IService service;

    public ExportOrderToCsv(IService service) {
        this.service = service;
    }

    public String prepareData(){
        List<Order> tempOrder = service.getRepo();
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
        return sb.toString();
    }
}















