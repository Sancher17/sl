package com.model;

import javax.persistence.*;

@Entity
@Table
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "parent_id")
    private String parent_id;

    @Override
    public String toString() {
        return "Categories{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parent_id='" + parent_id + '\'' +
                '}';
    }
}
