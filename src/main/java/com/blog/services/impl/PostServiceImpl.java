package com.blog.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.constants.BlogApplicationConstant;
import com.blog.dtos.PostDto;
import com.blog.dtos.PostResponse;
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
				.orElseThrow(() -> new ResourceNotFoundException(BlogApplicationConstant.USER, BlogApplicationConstant.ID, userId));
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException(BlogApplicationConstant.CATEGORY, BlogApplicationConstant.ID, categoryId));
		
		Post post = blogSchemaMapper.toPost(postReq);
		post.setImg("default.png");
		post.setCategory(category);
		post.setUser(user);
		
		Post savedPost = postRepo.save(post);
		return blogSchemaMapper.toPostDto(savedPost);
	}

	@Override
	public PostDto updatePost(PostDto postReq, Long postId) {
		Post post = postRepo.findById(postId)
				.orElseThrow(()-> new ResourceNotFoundException(BlogApplicationConstant.POST, BlogApplicationConstant.ID, postId));
		post.setContent(postReq.getContent());
		post.setTitle(postReq.getTitle());
		Post updatedPost = postRepo.save(post);
		return blogSchemaMapper.toPostDto(updatedPost);
	}

	@Override
	public PostDto getPostById(Long postId) {
		Post post = postRepo.findById(postId)
				.orElseThrow(()-> new ResourceNotFoundException(BlogApplicationConstant.POST, BlogApplicationConstant.ID, postId));
		return blogSchemaMapper.toPostDto(post);
	}

	@Override
	public PostResponse getAllPosts(Integer pageSize, Integer pageNo, String sortBy) {
		PostResponse postRes = new PostResponse();
		List<Post> postList = new ArrayList<>();
		if(pageSize!=null && pageNo!=null) {
			Pageable p = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
			Page<Post> pageObj = postRepo.findAll(p);
			postList = pageObj.getContent();
			setPageProperties(postRes, pageObj);
		}
		else {
			postList = postRepo.findAll();
		}
		postRes.setContent(postList.stream().map(post -> blogSchemaMapper.toPostDto(post))
				.collect(Collectors.toList()));
		return postRes;
	}

	private void setPageProperties(PostResponse postRes, Page<Post> pageObj) {
		postRes.setPageNo(pageObj.getNumber());
		postRes.setPageSize(pageObj.getSize());
		postRes.setTotalPages(pageObj.getTotalPages());
		postRes.setTotalElements(pageObj.getTotalElements());
		postRes.setLastPage(pageObj.isLast());
	}

	@Override
	public void deletePost(Long postId) {
		Post post = postRepo.findById(postId)
				.orElseThrow(()-> new ResourceNotFoundException(BlogApplicationConstant.POST, BlogApplicationConstant.ID, postId));
		postRepo.delete(post);
	}

	@Override
	public List<PostDto> getPostsByCategory(Long categoryId) {
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException(BlogApplicationConstant.CATEGORY, BlogApplicationConstant.ID, categoryId));
		List<Post> posts = postRepo.findByCategory(category);
		List<PostDto> postsDtoList = posts.stream().map(post -> blogSchemaMapper.toPostDto(post)).collect(Collectors.toList());
		return postsDtoList;
	}

	@Override
	public List<PostDto> getPostsByUser(Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException(BlogApplicationConstant.USER, BlogApplicationConstant.ID, userId));
		List<Post> posts = postRepo.findByUser(user);
		List<PostDto> postsDtoList = posts.stream().map(post -> blogSchemaMapper.toPostDto(post)).collect(Collectors.toList());
		return postsDtoList;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post> posts = postRepo.findByTitleContaining(keyword);
		return posts.stream()
				.map(post -> blogSchemaMapper.toPostDto(post)).collect(Collectors.toList());
	}

}
