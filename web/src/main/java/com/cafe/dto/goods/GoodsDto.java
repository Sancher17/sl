package com.cafe.dto.goods;

import com.cafe.model.Goods;

public class GoodsDto {

    private Long id;
    private String nameGoodsDto;
    private String category;
    private String goodsSize;
    private Double purchasePrice;
    private Double sellPrice;
    private Double weight;
    private Double volume;

    public GoodsDto(Goods goods) {
        this.id = goods.getId();
        this.nameGoodsDto = goods.getNameGoods().getName();
        this.category = goods.getCategory().getName();
        this.goodsSize = goods.getSizeGoods().toString();
        this.purchasePrice = goods.getPurchasePrice();
        this.sellPrice = goods.getSellPrice();
        this.weight = goods.getWeight();
        this.volume = goods.getVolume();
    }

    public GoodsDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameGoodsDto() {
        return nameGoodsDto;
    }

    public void setNameGoodsDto(String nameGoodsDto) {
        this.nameGoodsDto = nameGoodsDto;
    }

    public String getGoodsSize() {
        return goodsSize;
    }

    public void setGoodsSize(String goodsSize) {
        this.goodsSize = goodsSize;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
