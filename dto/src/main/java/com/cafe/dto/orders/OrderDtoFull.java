package com.cafe.dto.orders;

import com.cafe.dto.goods.GoodsDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

public class OrderDtoFull {

    private Long id;
    private String created;
    private Long userId;
    private String userFirstName;
    private String userLastName;
    private Double amount;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<GoodsDto> goods;


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
