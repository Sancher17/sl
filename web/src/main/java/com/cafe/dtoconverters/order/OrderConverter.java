package com.cafe.dtoconverters.order;

import com.cafe.api.dtoconverters.IOrderConverter;
import com.cafe.api.services.IGoodsService;
import com.cafe.api.services.IUserService;
import com.cafe.dto.orders.OrderDtoFull;
import com.cafe.dto.orders.OrderDtoSimple;
import com.cafe.model.Goods;
import com.cafe.model.Order;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class OrderConverter implements IOrderConverter {

    @Autowired
    private IUserService userService;
    @Autowired
    private IGoodsService goodsService;

    @Override
    public Order toModel(OrderDtoFull dto) {
        return null;
    }

    @Override
    public OrderDtoFull toDto(Order entity) {
        return null;
    }

    @Override
    public Order updateEntity(Order entity, OrderDtoFull dto) {
        return null;
    }

    @Override
    public Order simpleToModel(OrderDtoSimple dtoSimple, Long id) {
        Order order = new Order();
        order.setId(id);
        order.setAmount(dtoSimple.getAmount());
        order.setCreated(LocalDateTime.now());
        order.setUser(userService.getById(dtoSimple.getUserId()));
        order.setGoods(listGoodsToModel(dtoSimple.getListGoodsId()));
        return order;
    }

    private List<Goods> listGoodsToModel(List<Long> dtoGoodsIdList){
        return dtoGoodsIdList.stream()
                .map(dtoDataFromList -> goodsService.getById(dtoDataFromList))
                .collect(Collectors.toList());
    }
}
