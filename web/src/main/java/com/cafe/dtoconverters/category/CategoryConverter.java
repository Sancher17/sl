package com.cafe.dtoconverters.category;

import com.cafe.api.dtoconverters.ICategoryConverter;
import com.cafe.api.services.ICategoryService;
import com.cafe.dto.category.CategoryDto;
import com.cafe.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter implements ICategoryConverter {

    @Autowired
    ICategoryService categoryService;


    @Override
    public Category toModel(CategoryDto categoryDto) {
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getCategory());
        category.setParentCategory(
                categoryService.getById(categoryDto.getParentCategoryId()));
        return category;
    }

    @Override
    public CategoryDto toDto(Category entity) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(entity.getId());
        categoryDto.setCategory(entity.getName());
        categoryDto.setParentCategory(entity.getParentCategory().getName());
        categoryDto.setParentCategoryId(entity.getParentCategory().getId());
        return categoryDto;
    }

    @Override
    public Category updateEntity(Category entity, CategoryDto dto) {
        return null;
    }
}
