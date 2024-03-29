package com.blog.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotEmpty;
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
public class UserDto {

	private Long id;
	@NotEmpty(message = "name is missing")
	private String name;
	@NotEmpty(message = "email is missing")
	private String email;
	private String password;
	private String about;
	
	private String message;
	private String createdAt;
}
