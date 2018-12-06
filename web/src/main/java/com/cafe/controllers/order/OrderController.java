package com.cafe.controllers.order;

import com.cafe.api.services.*;
import com.cafe.dto.goods.GoodsDto;
import com.cafe.dto.orders.OrderDto;
import com.cafe.dto.orders.OrderDtoSimpleData;
import com.cafe.model.*;
import com.cafe.model.enums.GoodsSize;
import com.cafe.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IUserService userService;

    @Autowired
    private INameGoodsService nameGoodsService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IGoodsService goodsService;

    @GetMapping(value = "/full")
    public List<OrderDto> getAllFullData() {
        return orderService.getAll()
                .stream().map(OrderDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/")
    public List<OrderDtoSimpleData> getAll() {
        return orderService.getAll()
                .stream().map(OrderDtoSimpleData::new)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public OrderDto getById(@PathVariable("id") Long id) {
        return new OrderDto(orderService.getById(id));
    }

    @PutMapping
    public void create(@RequestBody OrderDto orderDto) {
        orderService.add(dtoToModel(orderDto));
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Long id) {
        orderService.delete(id);
    }

    @PostMapping(value = "/{id}")
    public void update(@RequestBody OrderDto orderDto, @PathVariable("id") Long id) {
        Order order = orderService.getById(id);
        order = dtoToModel(orderDto);
        orderService.update(order);
    }

    private Order dtoToModel(OrderDto orderDto) {
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setCreated(DateUtil.dateFromString(orderDto.getCreated()));
        User user = userService.getById(orderDto.getUserId());
        order.setUser(user);
        order.setGoods(listGoodsToModel(orderDto.getGoods()));
        return order;
    }

    private List<Goods> listGoodsToModel(List<GoodsDto> dtoList){
        List<Goods> goodsList = new ArrayList<>();
        for (GoodsDto goodsDto : dtoList) {
            Goods goods = goodsService.getById(goodsDto.getId());
            goodsList.add(goods);
        }
        return goodsList;
    }
}
