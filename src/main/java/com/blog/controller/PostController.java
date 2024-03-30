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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.constants.BlogApplicationConstant;
import com.blog.dtos.PostDto;
import com.blog.dtos.PostResponse;
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
	@PutMapping(path = "/posts/{postId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PostDto> updateCategory(@PathVariable String postId, @Valid @RequestBody PostDto postReq) {
		PostDto updatedPost = postService.updatePost(postReq, Long.parseLong(postId));
		 return new ResponseEntity<>(updatedPost, HttpStatus.OK);
	}
	
	/*
	 * Get post details based on postId
	 */
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getCategoryById(@PathVariable String postId) {
		PostDto postDto = postService.getPostById(Long.parseLong(postId));
		 return new ResponseEntity<>(postDto, HttpStatus.OK);
	}
	
	/*
	 * Get all posts
	 */
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(name = "size", required = false) Integer pageSize, 
			@RequestParam(name = "page", required = false) Integer pageNo,
			@RequestParam(name = "sortBy", defaultValue = "postId", required = false) String sortBy) {
		PostResponse allPosts = postService.getAllPosts(pageSize, pageNo, sortBy);
		 return new ResponseEntity<>(allPosts, HttpStatus.OK);
	}
	
	/*
	 * Delete a post
	 */
	@DeleteMapping(path = "/posts/{postId}")
	public ResponseEntity<Map<String, String>> deleteCategoryById(@PathVariable String postId) {
		postService.deletePost(Long.parseLong(postId));
		Map<String, String> map = new HashMap<String, String>();
		map.put(BlogApplicationConstant.MSG, BlogApplicationConstant.DELETE_SUCCESS);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
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
	
	/*
	 * search posts by keyword in title
	 */
	@GetMapping(path = "/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPosts(@PathVariable String keywords) {
		List<PostDto> postsDto = postService.searchPosts(keywords);
		return ResponseEntity.ok(postsDto);
	}
}
