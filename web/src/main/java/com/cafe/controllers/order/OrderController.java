package com.cafe.controllers.order;

import com.cafe.api.dtoconverters.IOrderConverter;
import com.cafe.api.services.*;
import com.cafe.dto.orders.OrderDtoFull;
import com.cafe.dto.orders.OrderDtoSimple;
import com.cafe.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IOrderConverter orderConverter;

    @GetMapping(value = "/full")
    public List<OrderDtoFull> getAllFullData() {
        return orderService.getAll()
                .stream().map(OrderDtoFull::new)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/")
    public List<OrderDtoSimple> getAll() {
        return orderService.getAll()
                .stream().map(OrderDtoSimple::new)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public OrderDtoFull getById(@PathVariable("id") Long id) {
        return new OrderDtoFull(orderService.getById(id));
    }

    @PutMapping
    public void create(@RequestBody OrderDtoSimple orderDtoSimple) {
        orderService.add(orderConverter.simpleToModel(orderDtoSimple, null));
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Long id) {
        orderService.delete(id);
    }

    @PostMapping(value = "/{id}")
    public void update(@RequestBody OrderDtoSimple orderDtoSimple, @PathVariable("id") Long id) {
        Order order = orderService.getById(id);
        order = orderConverter.simpleToModel(orderDtoSimple, id);
        orderService.update(order);
    }

}
