package com.model;

import javax.persistence.*;

@Entity
@Table(name = "price_modifier")
public class PriceModifier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "discount")
    private Double discount;

    @Column(name = "markup")
    private Double markup;

    @Column(name = "category_id")
    private Long category_id;

    @Column(name = "goods_id")
    private Long goods_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getMarkup() {
        return markup;
    }

    public void setMarkup(Double markup) {
        this.markup = markup;
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
        return "PriceModifier{" +
                "id=" + id +
                ", discount=" + discount +
                ", markup=" + markup +
                ", category_id=" + category_id +
                ", goods_id=" + goods_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceModifier priceModifier = (PriceModifier) o;
        return id.equals(priceModifier.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
