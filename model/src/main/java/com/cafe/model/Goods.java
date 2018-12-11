package com.cafe.model;

import com.cafe.model.enums.GoodsSize;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "goods")
public class Goods extends GenericEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "name_id")
    private NameGoods nameGoods;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "size")
    @Enumerated(EnumType.STRING)
    private GoodsSize sizeGoods;

    @Column(name = "purchase_price")
    private Double purchasePrice;

    @Column(name = "sell_price")
    private Double sellPrice;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "volume")
    private Double volume;


    public NameGoods getNameGoods() {
        return nameGoods;
    }

    public void setNameGoods(NameGoods nameGoods) {
        this.nameGoods = nameGoods;
    }

    public GoodsSize getSizeGoods() {
        return sizeGoods;
    }

    public void setSizeGoods(GoodsSize sizeGoods) {
        this.sizeGoods = sizeGoods;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Goods{");
        sb.append("id=").append(getId());
        sb.append("nameGoods=").append(nameGoods);
        sb.append(", category=").append(category);
        sb.append(", sizeGoods=").append(sizeGoods);
        sb.append(", purchasePrice=").append(purchasePrice);
        sb.append(", sellPrice=").append(sellPrice);
        sb.append(", weight=").append(weight);
        sb.append(", volume=").append(volume);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goods goods = (Goods) o;
        return getId().equals(goods.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
