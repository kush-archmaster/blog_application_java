package com.blog.services;

import java.util.List;

import com.blog.dtos.PostDto;

public interface PostService {

	PostDto createPost(PostDto postReq, Long userId, Long categoryId);
	PostDto updatePost(PostDto postReq, Long postId);
	PostDto getPostById(Long postId);
	List<PostDto> getAllPosts();
	void deletePost(Long postId);
	
	List<PostDto> getPostsByCategory(Long categoryId);
	List<PostDto> getPostsByUser(Long userId);
}
