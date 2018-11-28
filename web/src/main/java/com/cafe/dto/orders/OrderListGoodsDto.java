package com.cafe.dto.orders;

import com.cafe.model.Goods;
import com.cafe.model.Order;

import java.util.List;

public class OrderListGoodsDto {

    private List<Goods> goodsList;

    public OrderListGoodsDto(Order order) {
        this.goodsList = order.getGoods();
    }

    public OrderListGoodsDto() {
    }

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }
}
