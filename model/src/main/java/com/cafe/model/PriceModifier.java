package com.cafe.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "price_modifier")
public class PriceModifier extends GenericEntity {

    @Column(name = "discount")
    private Double discount;

    @Column(name = "markup")
    private Double markup;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goods_id")
    private Goods goods;


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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PriceModifier{");
        sb.append("id=").append(getId());
        sb.append(", discount=").append(discount);
        sb.append(", markup=").append(markup);
        sb.append(", category=").append(category);
        sb.append(", goods=").append(goods);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceModifier priceModifier = (PriceModifier) o;
        return getId().equals(priceModifier.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
