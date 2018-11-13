package com.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Column(name = "created")
    private Date created;

    @Column(name = "user_id")
    private Long user_id;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "order_goods",
            joinColumns = {@JoinColumn(name = "order_id")},
            inverseJoinColumns = {@JoinColumn(name = "goods_id")})
    private List<Goods> goods;

    public List<Goods> getGoods() {
        return goods;
    }

    public Long getId() {
        return id;
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

    public void setGoods(List<Goods> goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", created=" + created +
                ", user_id=" + user_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        if (!id.equals(order.id)) return false;
        return user_id != null ? user_id.equals(order.user_id) : order.user_id == null;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
