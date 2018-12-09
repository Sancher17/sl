package com.cafe.api.dtoconverters;

import com.cafe.dto.category.CategoryDto;
import com.cafe.model.Category;
import org.springframework.stereotype.Component;

@Component
public interface ICategoryConverter extends GenericConverter<Category, CategoryDto> {
}
