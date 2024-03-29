package com.blog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.dtos.CategoryDto;
import com.blog.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	
	/*
	 * Create a new Category
	 */
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryReq) {
		 CategoryDto CategoryDto = categoryService.createCategory(categoryReq);
		 return new ResponseEntity<CategoryDto>(CategoryDto, HttpStatus.CREATED);
	}
	
	/*
	 * Update information of existing Category
	 */
	@PatchMapping(path = "/{categoryId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CategoryDto> updateCategory(@PathVariable String categoryId,@Valid @RequestBody CategoryDto categoryReq) {
		 CategoryDto updateCategory = categoryService.updateCategory(categoryReq, Long.parseLong(categoryId));
		 return new ResponseEntity<>(updateCategory, HttpStatus.OK);
	}
	
	/*
	 * Get Category details based on CategoryId
	 */
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable String categoryId) {
		 CategoryDto categoryDto = categoryService.getCategoryById(Long.parseLong(categoryId));
		 return new ResponseEntity<>(categoryDto, HttpStatus.OK);
	}
	
	/*
	 * Get all categories
	 */
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategories() {
		 List<CategoryDto> allCategories = categoryService.getAllCategories();
		 return new ResponseEntity<>(allCategories, HttpStatus.OK);
	}
	
	/*
	 * Delete a Category
	 */
	@DeleteMapping(path = "/{categoryId}")
	public ResponseEntity<Map<String, String>> deleteCategoryById(@PathVariable String categoryId) {
		categoryService.deleteCategory(Long.parseLong(categoryId));
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", "Deleted successfully");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
}
