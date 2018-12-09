package com.cafe.api.dtoconverters;

import com.cafe.dto.orders.OrderDtoFull;
import com.cafe.dto.orders.OrderDtoSimple;
import com.cafe.model.Order;
import org.springframework.stereotype.Component;

@Component
public interface IOrderConverter extends GenericConverter<Order, OrderDtoFull> {

    Order simpleToModel(OrderDtoSimple dtoSimple, Long id);
}
