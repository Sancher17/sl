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

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", created=" + created +
                ", user_id=" + user_id +
                '}';
    }
}
