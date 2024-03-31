package com.blog.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.constants.BlogApplicationConstant;
import com.blog.dtos.CommentDto;
import com.blog.services.CommentService;

@RestController
@RequestMapping("/api/v1")
public class CommentController {
	
	@Autowired
	private CommentService commentService;

	/*
	 * Create a new Comment
	 */
	@PostMapping(path= "/user/{userId}/post/{postId}/comments", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CommentDto> createComment(
			@PathVariable String userId, 
			@PathVariable String postId,
			@RequestBody CommentDto commentReq) {
 		CommentDto comment = commentService.createComment(commentReq, Long.parseLong(postId), Long.parseLong(userId));
		return new ResponseEntity<>(comment, HttpStatus.CREATED);
	}
	
	/*
	 * Delete a comment
	 */
	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<Map<String, String>> deleteComment(@PathVariable String commentId) {
		commentService.deleteComment(Long.parseLong(commentId));
		Map<String, String> map = new HashMap<String, String>();
		map.put(BlogApplicationConstant.MSG, BlogApplicationConstant.DELETE_SUCCESS);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
}
