package com.model;

import javax.persistence.*;

@Entity
@Table(name = "names_goods")
public class NameGoods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "category_id")
    private Long category_id;

    @Override
    public String toString() {
        return "NameGoods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category_id=" + category_id +
                '}';
    }
}
