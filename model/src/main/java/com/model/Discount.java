package com.model;

import javax.persistence.*;

@Entity
@Table(name = "Discounts")
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "factor")
    private Double factor;

    @Column(name = "category_id")
   private Long category_id;

    @Column(name = "goods_id")
    private Long goods_id;

    public Long getId() {
        return id;
    }

    public Double getFactor() {
        return factor;
    }

    public void setFactor(Double factor) {
        this.factor = factor;
    }

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }

    public Long getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(Long goods_id) {
        this.goods_id = goods_id;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "id=" + id +
                ", factor=" + factor +
                ", category_id=" + category_id +
                ", goods_id=" + goods_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discount discount = (Discount) o;
        return id.equals(discount.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
