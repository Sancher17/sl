package com.cafe.dto.orders;

import com.cafe.model.Order;

import java.util.Date;

public class OrderDto {

    private Long id;
    private Date created;
    private String userLastName;
    private Double amount;

    public OrderDto(Order order) {
        this.id = order.getId();
        this.created = order.getCreated();
//        this.userLastName = order.getUserLastName();
        this.amount = order.getAmount();
    }


}
