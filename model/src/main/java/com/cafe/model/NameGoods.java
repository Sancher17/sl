package com.cafe.model;

import javax.persistence.*;

@Entity
@Table(name = "names_goods")
public class NameGoods extends GenericEntity{

    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("NameGoods{");
        sb.append("id='").append(getId()).append('\'');
        sb.append("name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NameGoods nameGoods = (NameGoods) o;
        return getId().equals(nameGoods.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
