package com.model;

import javax.persistence.*;

@Entity
@Table(name = "Discounts")
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "factor")
    private Double factor;

    @Column(name = "category_id")
   private Long category_id;

    @Column(name = "goods_id")
    private Long goods_id;

    @Override
    public String toString() {
        return "Discount{" +
                "id=" + id +
                ", factor=" + factor +
                ", category_id=" + category_id +
                ", goods_id=" + goods_id +
                '}';
    }
}
