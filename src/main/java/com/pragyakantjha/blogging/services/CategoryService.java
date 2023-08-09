package com.pragyakantjha.blogging.services;

import com.pragyakantjha.blogging.payload.CategoryDto;

import java.util.List;

public interface CategoryService {
    // create
    CategoryDto createCategory(CategoryDto categoryDto);
    // update
    CategoryDto updateCategory(CategoryDto newCategoryDto, Integer categoryId);
    // delete
    void deleteCategory(Integer categoryId);
    // get
    CategoryDto getCategory(Integer categoryId);
    // get all
    List<CategoryDto> getAllCategory();
}
