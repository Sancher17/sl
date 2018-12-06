package com.cafe.model;

import javax.persistence.*;

@Entity
@Table(name = "storage")
public class Storage extends GenericEntity {

    @Column(name = "quantity")
    private Integer quantity;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "goods_id")
    private Goods goods;


    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Storage{");
        sb.append("id=").append(getId());
        sb.append(", quantity=").append(quantity);
        sb.append(", goods=").append(goods);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Storage storage = (Storage) o;
        return getId().equals(storage.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
