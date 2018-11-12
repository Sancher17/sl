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

    @Column(name = "quantity")
    private Integer quantity;

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + id +
                ", goods_id=" + goods_id +
                ", quantity=" + quantity +
                '}';
    }
}
