package com.cafe.controllers.category;

import com.cafe.api.services.ICategoryService;
import com.cafe.dto.category.CategoryDto;
import com.cafe.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping(value = "/")
    public List<CategoryDto> getAll() {
        return categoryService.getAll()
                .stream().map(CategoryDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public CategoryDto getById(@PathVariable("id") Long id) {
        return new CategoryDto(categoryService.getById(id));
    }

    @PostMapping(value = "/")
    public void update(@RequestBody CategoryDto categoryDto) {
        categoryService.update(categoryDto.toModel());
    }

    @PutMapping
    public void create(@RequestBody CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getCategoryName());
        Category parentCategory = categoryService.getByName(categoryDto.getParentCategoryName());
        category.setParentCategory(parentCategory);
        categoryService.add(category);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Long id) {
        categoryService.delete(id);
    }

}