package com.cafe.dto.category;

import com.cafe.dto.AbstractDto;
import com.cafe.model.Category;


public class CategoryDto extends AbstractDto {

    private Long id;
    private String category;
    private String parentCategory;
    private Long parentCategoryId;

    public CategoryDto(Category category) {
        this.id = category.getId();
        this.category = category.getName();
        this.parentCategory = category.getParentCategory().getName();
        this.parentCategoryId = category.getParentCategory().getId();
    }

//    public Category fromDtoToModel(CategoryDto categoryDto) {
//        Category category = new Category();
//        category.setId(categoryDto.getId());
//        category.setName(categoryDto.getName());
//        category.setParentCategory(categoryDto.getParentCategory());
//        return category;
//    }

    public CategoryDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(String parentCategory) {
        this.parentCategory = parentCategory;
    }

    public Long getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Long parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }
}
