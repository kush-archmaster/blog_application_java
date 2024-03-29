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
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class CategoryDto {

	private Long categoryId;
	@NotEmpty
	@Size(min = 3, message = "Category name should be min 3 characters!")
	private String categoryName;
	@NotEmpty
	private String description;
}
