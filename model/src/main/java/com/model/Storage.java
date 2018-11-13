package com.model;

import javax.persistence.*;

@Entity
@Table(name = "storage")
public class Storage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "goods_id")
    private Long goods_id;

    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "supplyorder_id")
    private Long supplyorder_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(Long goods_id) {
        this.goods_id = goods_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getSupplyorder_id() {
        return supplyorder_id;
    }

    public void setSupplyorder_id(Long supplyorder_id) {
        this.supplyorder_id = supplyorder_id;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + id +
                ", goods_id=" + goods_id +
                ", user_id=" + user_id +
                ", quantity=" + quantity +
                ", supplyorder_id=" + supplyorder_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Storage storage = (Storage) o;

        return id.equals(storage.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
