package com.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dtos.CategoryDto;
import com.blog.entities.Category;
import com.blog.exception.ResourceNotFoundException;
import com.blog.mapper.BlogSchemaMapper;
import com.blog.repositories.CategoryRepository;
import com.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@Autowired
	private BlogSchemaMapper blogSchemaMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryReq) {
		Category category = blogSchemaMapper.toCategory(categoryReq);
		Category savedCategory = categoryRepo.save(category);
		return blogSchemaMapper.toCategoryDto(savedCategory);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryReq, Long categoryId) {
		Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "ID", categoryId));
		updateCategoryDate(category, categoryReq);
		categoryRepo.save(category);
		return blogSchemaMapper.toCategoryDto(category);
	}

	private void updateCategoryDate(Category category, CategoryDto categoryReq) {
		category.setCategoryName(categoryReq.getCategoryName());
		category.setDescription(categoryReq.getDescription());
	}

	@Override
	public CategoryDto getCategoryById(Long categoryId) {
		Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "ID", categoryId));
		return blogSchemaMapper.toCategoryDto(category);
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		List<Category> categoryList = categoryRepo.findAll();
		List<CategoryDto> categoryListDto = categoryList.stream().map((categ) -> blogSchemaMapper.toCategoryDto(categ))
				.collect(Collectors.toList());
		return categoryListDto;
	}

	@Override
	public void deleteCategory(Long categoryId) {
		Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "ID", categoryId));
		categoryRepo.delete(category);
	}

}
