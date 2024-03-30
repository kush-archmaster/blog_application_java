package com.blog.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
	private CategoryDto category;
	private UserDto user;
}
