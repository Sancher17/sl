package com.cafe.model;

import javax.persistence.*;

@Entity
@Table(name = "goods")
@Inheritance(strategy = InheritanceType.JOINED)
@SecondaryTables({
        @SecondaryTable(name = "names_goods"),
        @SecondaryTable(name = "size_goods")})
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", table = "names_goods")
    private String name;

    @Column(name = "name", table = "size_goods")
    private String size;

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


    public String getName() {
        return name;
    }

    public void setName(String nameId) {
        this.name = nameId;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String sizeId) {
        this.size = sizeId;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchase_price) {
        this.purchasePrice = purchase_price;
    }

    public Double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Double sell_price) {
        this.sellPrice = sell_price;
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
                ", name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", purchase_price=" + purchasePrice +
                ", sell_price=" + sellPrice +
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
