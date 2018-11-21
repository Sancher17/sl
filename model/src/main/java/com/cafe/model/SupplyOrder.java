package com.cafe.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "supply_orders")
public class SupplyOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Column(name = "created")
    private Date created;

    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "amount")
    private Double amount;

    // TODO: 13.11.2018 сделать
    private List<Goods> goods;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public List<Goods> getGoods() {
        return goods;
    }

    public void setGoods(List<Goods> goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "SupplyOrder{" +
                "id=" + id +
                ", created=" + created +
                ", user_id=" + user_id +
                ", amount=" + amount +
                ", goods=" + goods +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SupplyOrder order = (SupplyOrder) o;
        if (!id.equals(order.id)) return false;
        return user_id != null ? user_id.equals(order.user_id) : order.user_id == null;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
