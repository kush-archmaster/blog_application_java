package com.blog.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.constants.BlogApplicationConstant;
import com.blog.dtos.CommentDto;
import com.blog.entities.Comment;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.exception.ResourceNotFoundException;
import com.blog.mapper.BlogSchemaMapper;
import com.blog.repositories.CommentRepository;
import com.blog.repositories.PostRepository;
import com.blog.repositories.UserRepository;
import com.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepo;

	@Autowired
	private PostRepository postRepo;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BlogSchemaMapper blogSchemaMapper;

	@Override
	public CommentDto createComment(CommentDto commentReq, Long postId, Long userId) {
		User user = userRepository.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException(BlogApplicationConstant.USER, BlogApplicationConstant.ID, userId));
		Post post = postRepo.findById(postId).orElseThrow(
				() -> new ResourceNotFoundException(BlogApplicationConstant.POST, BlogApplicationConstant.ID, postId));

		Comment comment = blogSchemaMapper.toComment(commentReq);
		comment.setUser(user);
		comment.setPost(post);
		Comment savedComment = commentRepo.save(comment);
		return blogSchemaMapper.toCommentDto(savedComment);
	}

	@Override
	public void deleteComment(Long commentId) {
		Comment comment = commentRepo.findById(commentId).orElseThrow(
				() -> new ResourceNotFoundException(BlogApplicationConstant.COMMENT, BlogApplicationConstant.ID, commentId));
		commentRepo.delete(comment);
	}

}
