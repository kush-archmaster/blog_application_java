package com.blog.dtos;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostDto {

	private Long postId;
	@NotEmpty
	@Size(min = 5, message = "Title should be minimum 5 characters!")
	private String title;
	@NotEmpty
	@Size(min = 100, message = "Content should be greated than 100 characters!")
	private String content;
	private String createdAt;
	private String image;
	private CategoryDto category;
	private UserDto user;
	private Set<CommentDto> comments=new HashSet<>(); 
}
