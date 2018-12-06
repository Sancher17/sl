package com.cafe.dto.namegoods;

import com.cafe.model.Category;
import com.cafe.model.NameGoods;
import com.fasterxml.jackson.annotation.JsonView;

@JsonView
public class NameGoodsDto {

    private Long id;
    private String name;
    private Category category;


    public NameGoodsDto(NameGoods nameGoods) {
        this.id = nameGoods.getId();
        this.name = nameGoods.getName();

    }

    public NameGoodsDto() {
    }

    public NameGoods fromDtoToModel(NameGoodsDto nameGoodsDto){
        NameGoods nameGoods = new NameGoods();
        nameGoods.setId(nameGoodsDto.getId());
        return nameGoods;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("NameGoodsDto{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", category='").append(category).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
