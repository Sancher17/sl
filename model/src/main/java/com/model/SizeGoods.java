package com.model;

import javax.persistence.*;

@Entity
@Table(name = "size_goods")
public class SizeGoods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Override
    public String toString() {
        return "SizeGoods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
