package com.cafe.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "goods")
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "name_id")
    private NameGoods nameGoods;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "size_id")
    private SizeGoods sizeGoods;

    @Column(name = "purchase_price")
    private Double purchasePrice;

    @Column(name = "sell_price")
    private Double sellPrice;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "volume")
    private Double volume;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NameGoods getNameGoods() {
        return nameGoods;
    }

    public void setNameGoods(NameGoods nameGoods) {
        this.nameGoods = nameGoods;
    }

    public SizeGoods getSizeGoods() {
        return sizeGoods;
    }

    public void setSizeGoods(SizeGoods sizeGoods) {
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

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", nameGoods=" + nameGoods +
                ", sizeGoods=" + sizeGoods +
                ", purchasePrice=" + purchasePrice +
                ", sellPrice=" + sellPrice +
                ", weight=" + weight +
                ", volume=" + volume +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goods goods = (Goods) o;
        return id.equals(goods.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
