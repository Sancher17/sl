package com.cafe.controllers.category;

import com.cafe.api.dtoconverters.ICategoryConverter;
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

    @Autowired
    private ICategoryConverter categoryConverter;

    @GetMapping(value = "/all")
    public List<CategoryDto> getAll() {
        return categoryService.getAll()
                .stream().map(CategoryDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public CategoryDto getById(@PathVariable("id") Long id) {
        return categoryConverter.toDto(categoryService.getById(id));
    }

    @PostMapping(value = "/{id}")
    public void update(@RequestBody CategoryDto categoryDto, @PathVariable("id") Long id) { ;
        Category category = categoryService.getById(id);
        category = categoryConverter.toModel(categoryDto);
        categoryService.update(category);
    }

    @PutMapping
    public void create(@RequestBody CategoryDto categoryDto) {
        categoryService.add(categoryConverter.toModel(categoryDto));
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Long id) {
        categoryService.delete(id);
    }

}