package com.cafe.model;

import javax.persistence.*;

@Entity
@Table(name = "names_goods")
@Inheritance(strategy = InheritanceType.JOINED)
@SecondaryTable(name = "categories")
public class NameGoods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "name", table = "categories")
    private String categoryName;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "NameGoods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + categoryName +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NameGoods nameGoods = (NameGoods) o;
        return id.equals(nameGoods.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
