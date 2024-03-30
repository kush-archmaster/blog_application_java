package com.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dtos.PostDto;
import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.exception.ResourceNotFoundException;
import com.blog.mapper.BlogSchemaMapper;
import com.blog.repositories.CategoryRepository;
import com.blog.repositories.PostRepository;
import com.blog.repositories.UserRepository;
import com.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostRepository postRepo;
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BlogSchemaMapper blogSchemaMapper;
	
	@Override
	public PostDto createPost(PostDto postReq, Long userId, Long categoryId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category", "ID", categoryId));
		
		Post post = blogSchemaMapper.toPost(postReq);
		post.setImg("default.png");
		post.setCategory(category);
		post.setUser(user);
		
		Post savedPost = postRepo.save(post);
		return blogSchemaMapper.toPostDto(savedPost);
	}

	@Override
	public PostDto updatePost(PostDto postReq, Long postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PostDto getPostById(Long postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostDto> getAllPosts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePost(Long postId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<PostDto> getPostsByCategory(Long categoryId) {
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category", "ID", categoryId));
		List<Post> posts = postRepo.findByCategory(category);
		List<PostDto> postsDtoList = posts.stream().map(post -> blogSchemaMapper.toPostDto(post)).collect(Collectors.toList());
		return postsDtoList;
	}

	@Override
	public List<PostDto> getPostsByUser(Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		List<Post> posts = postRepo.findByUser(user);
		List<PostDto> postsDtoList = posts.stream().map(post -> blogSchemaMapper.toPostDto(post)).collect(Collectors.toList());
		return postsDtoList;
	}

}
