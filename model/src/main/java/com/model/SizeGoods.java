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

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SizeGoods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SizeGoods sizeGoods = (SizeGoods) o;
        return id.equals(sizeGoods.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
