package com.blog.services;

import java.util.List;

import com.blog.dtos.UserDto;

public interface UserService {

	UserDto registerUser(UserDto user);
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user, Long userId);
	UserDto getUserById(Long userId);
	List<UserDto> getAllUsers();
	void deleteUser(Long userId);
}
