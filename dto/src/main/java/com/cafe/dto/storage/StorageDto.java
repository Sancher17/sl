package com.cafe.dto.storage;

import com.cafe.dto.AbstractDto;
import com.cafe.model.Storage;
import com.fasterxml.jackson.annotation.JsonView;

@JsonView
public class StorageDto extends AbstractDto {

    private Long id;
    private Integer quantity;
    private Long goodsId;
    private String goodsName;
    private String goodsCategory;
    private String size;

    public StorageDto() {
    }

    public StorageDto(Storage storage) {
        this.id = storage.getId();
        this.goodsId = storage.getGoods().getId();
        this.quantity = storage.getQuantity();
        this.goodsName = storage.getGoods().getNameGoods().getName();
        this.goodsCategory = storage.getGoods().getCategory().getName();
        this.size = storage.getGoods().getSizeGoods().name();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsCategory() {
        return goodsCategory;
    }

    public void setGoodsCategory(String goodsCategory) {
        this.goodsCategory = goodsCategory;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

}
