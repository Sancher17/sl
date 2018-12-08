package com.cafe.dto.orders;

import com.cafe.dto.goods.GoodsDto;
import com.cafe.model.GenericEntity;
import com.cafe.model.Goods;
import com.cafe.model.Order;
import com.cafe.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderDtoFull {

    private Long id;
    private String created;
    private Long userId;
    private String userFirstName;
    private String userLastName;
    private Double amount;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<GoodsDto> goods;

    public OrderDtoFull(Order order) {
        this.id = order.getId();
        this.created = DateUtil.stringFromDate(order.getCreated());
        this.userId = order.getUser().getId();
        this.userFirstName = order.getUser().getFirstName();
        this.userLastName = order.getUser().getLastName();
        this.amount = order.getAmount();
        this.goods = listGoodsToDto(order.getGoods());
    }


    private List<GoodsDto> listGoodsToDto(List<Goods> goodsList){
        return goodsList.stream()
                .map(GoodsDto::new)
                .collect(Collectors.toList());
    }

    public OrderDtoFull() {
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

    public List<GoodsDto> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsDto> goods) {
        this.goods = goods;
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
}
