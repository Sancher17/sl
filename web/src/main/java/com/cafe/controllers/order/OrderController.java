package com.cafe.controllers.order;

import com.cafe.api.services.IOrderService;
import com.cafe.api.services.IUserService;
import com.cafe.dto.goods.GoodsDto;
import com.cafe.dto.orders.OrderDto;
import com.cafe.model.Goods;
import com.cafe.model.Order;
import com.cafe.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;
    @Autowired
    private IUserService userService;

    @GetMapping(value = "/full")
    public List<OrderDto> getAllFullData() {
        return orderService.getAll()
                .stream().map(OrderDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/")
    public List<OrderDto> getAll() {
        return orderService.getAll()
                .stream().map(OrderDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public OrderDto getById(@PathVariable("id") Long id) {
        return new OrderDto(orderService.getById(id));
    }

    @PutMapping
    public void create(@RequestBody OrderDto orderDto) {
        User user = userService.getById(orderDto.getUserId());
        Order order = orderDto.toModel();
        List<Goods> goodsList = orderDto.getGoodsList()
                .stream().map(GoodsDto::toModel).collect(Collectors.toList());
        order.setGoods(goodsList);
        order.setUser(user);
        orderService.add(order);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Long id) {
        orderService.delete(id);
    }

    @PostMapping(value = "/")
    public void update(@RequestBody OrderDto orderDto) {
        orderService.update(orderDto.toModel());
    }


}
