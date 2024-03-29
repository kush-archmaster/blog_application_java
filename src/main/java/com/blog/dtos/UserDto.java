package com.blog.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.Email;
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
public class UserDto {

	private Long id;
	
	@NotEmpty
	@Size(min = 4, message = "username must be greater than 4 characters!")
	private String name;
	
	@Email(message = "Email is invalid")
	private String email;
	
	@NotEmpty
	@Size(min=3, max = 10, message = "Password must be between 3 to 10 characters!")
	private String password;
	
	@NotEmpty
	private String about;
	
	private String message;
	private String createdAt;
}
