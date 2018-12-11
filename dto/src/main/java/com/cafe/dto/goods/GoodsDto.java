package com.cafe.dto.goods;

import com.cafe.model.Goods;
import com.cafe.model.enums.GoodsSize;

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
        this.goodsSize = goods.getSizeGoods().name();
        this.purchasePrice = goods.getPurchasePrice();
        this.sellPrice = goods.getSellPrice();
        this.weight = goods.getWeight();
        this.volume = goods.getVolume();
    }

    public Goods toModel(){
        Goods goods = new Goods();
        goods.setId(id);
        goods.setPurchasePrice(purchasePrice);
        goods.setSellPrice(sellPrice);
        goods.setWeight(weight);
        goods.setVolume(volume);
        goods.setSizeGoods(GoodsSize.valueOf(goodsSize));
        return goods;
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
