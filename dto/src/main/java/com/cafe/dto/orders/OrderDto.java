package com.cafe.dto.orders;

import com.cafe.dto.goods.GoodsDto;
import com.cafe.model.Goods;
import com.cafe.model.Order;
import com.cafe.utils.DateUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


public class OrderDto {

    private Long id;
    private String created;
    private Long userId;
    private String userFirstName;
    private String userLastName;
    private Double amount;
    private List<GoodsDto> goodsList;

    public OrderDto(Order order) {
        this.id = order.getId();
        this.created = DateUtil.stringFromDate(order.getCreated());
        this.userId = order.getUser().getId();
        this.userFirstName = order.getUser().getFirstName();
        this.userLastName = order.getUser().getLastName();
        this.amount = order.getAmount();
        this.goodsList = listGoodsToDto(order.getGoods());
    }

    public Order toModel() {
        Order order = new Order();
        order.setId(id);
        order.setAmount(amount);
        order.setCreated(LocalDateTime.now());
        return order;
    }

    public OrderDto() {
    }


    private List<GoodsDto> listGoodsToDto(List<Goods> goodsList) {
        return goodsList.stream()
                .map(GoodsDto::new)
                .collect(Collectors.toList());
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<GoodsDto> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<GoodsDto> goodsList) {
        this.goodsList = goodsList;
    }
}
