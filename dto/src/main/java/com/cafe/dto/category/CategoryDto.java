package com.cafe.dto.category;

import com.cafe.model.Category;


public class CategoryDto {

    private Long id;
    private String categoryName;
    private String parentCategoryName;
    private Long parentCategoryId;

    public CategoryDto(Category category) {
        this.id = category.getId();
        this.categoryName = category.getName();
        this.parentCategoryName = category.getParentCategory().getName();
        this.parentCategoryId = category.getParentCategory().getId();
    }

    public Category toModel(){
        Category category = new Category();
        category.setId(id);
        category.setName(categoryName);
        return category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getParentCategoryName() {
        return parentCategoryName;
    }

    public void setParentCategoryName(String parentCategoryName) {
        this.parentCategoryName = parentCategoryName;
    }

    public Long getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Long parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }
}
