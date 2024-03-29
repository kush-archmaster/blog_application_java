package com.blog.services;

import java.util.List;

import com.blog.dtos.CategoryDto;

public interface CategoryService {

	CategoryDto createCategory(CategoryDto categoryReq);
	CategoryDto updateCategory(CategoryDto categoryReq, Long categoryId);
	CategoryDto getCategoryById(Long categoryId);
	List<CategoryDto> getAllCategories();
	void deleteCategory(Long categoryId);
}
