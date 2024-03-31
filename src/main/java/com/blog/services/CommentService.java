package com.blog.services;

import com.blog.dtos.CommentDto;

public interface CommentService {

	CommentDto createComment(CommentDto commentReq, Long postId, Long userId);
	void deleteComment(Long commentId);
}
