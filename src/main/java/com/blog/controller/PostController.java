package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.dtos.PostDto;
import com.blog.services.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class PostController {

	@Autowired
	PostService postService;
	
	/*
	 * Create a new Category
	 */
	@PostMapping(path = "/user/{userId}/category/{categoryId}/post" , consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PostDto> createPost(
			@Valid @RequestBody PostDto postReq, 
			@PathVariable String userId, 
			@PathVariable String categoryId) {
		 PostDto postDto = postService.createPost(postReq, Long.parseLong(userId), Long.parseLong(categoryId));
		 return new ResponseEntity<>(postDto, HttpStatus.CREATED);
	}
	
	/*
	 * Update information of existing Category
	 */
//	@PatchMapping(path = "/{categoryId}", consumes = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<CategoryDto> updateCategory(@PathVariable String categoryId,@Valid @RequestBody CategoryDto categoryReq) {
//		 CategoryDto updateCategory = postService.updateCategory(categoryReq, Long.parseLong(categoryId));
//		 return new ResponseEntity<>(updateCategory, HttpStatus.OK);
//	}
//	
	/*
	 * Get Category details based on CategoryId
	 */
//	@GetMapping("/{categoryId}")
//	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable String categoryId) {
//		 CategoryDto categoryDto = postService.getCategoryById(Long.parseLong(categoryId));
//		 return new ResponseEntity<>(categoryDto, HttpStatus.OK);
//	}
	
	/*
	 * Get all categories
	 */
//	@GetMapping("/")
//	public ResponseEntity<List<CategoryDto>> getAllCategories() {
//		 List<CategoryDto> allCategories = postService.getAllCategories();
//		 return new ResponseEntity<>(allCategories, HttpStatus.OK);
//	}
	
	/*
	 * Delete a Category
	 */
//	@DeleteMapping(path = "/{categoryId}")
//	public ResponseEntity<Map<String, String>> deleteCategoryById(@PathVariable String categoryId) {
//		postService.deleteCategory(Long.parseLong(categoryId));
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("message", "Deleted successfully");
//		return new ResponseEntity<>(map, HttpStatus.OK);
//	}
	
	/*
	 * Get posts by categoryId
	 */
	@GetMapping(path = "/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategoryId(@PathVariable String categoryId) {
		List<PostDto> posts = postService.getPostsByCategory(Long.parseLong(categoryId));
		return ResponseEntity.ok(posts);
	}

	
	/*
	 * Get posts by userId
	 */
	@GetMapping(path = "/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUserId(@PathVariable String userId) {
		List<PostDto> posts = postService.getPostsByUser(Long.parseLong(userId));
		return ResponseEntity.ok(posts);
	}
}
