package com.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dtos.UserDto;
import com.blog.entities.User;
import com.blog.exception.ResourceNotFoundException;
import com.blog.mapper.BlogSchemaMapper;
import com.blog.repositories.UserRepository;
import com.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BlogSchemaMapper blogSchemaMapper;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = blogSchemaMapper.toUser(userDto);
		User savedUser = userRepository.save(user);
		return blogSchemaMapper.toUserDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		updateUserDetails(user, userDto);
		User savedUser = userRepository.save(user);
		return blogSchemaMapper.toUserDto(savedUser);
	}

	private void updateUserDetails(User userEntity, UserDto userDto) {
		userEntity.setName(userDto.getName());
		userEntity.setEmail(userDto.getEmail());
		userEntity.setPassword(userDto.getPassword());
		userEntity.setAbout(userDto.getAbout());
	}

	@Override
	public UserDto getUserById(Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		return blogSchemaMapper.toUserDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> userList = userRepository.findAll();
		List<UserDto> userDtoList = userList.stream()
				.map(user -> blogSchemaMapper.toUserDto(user)).collect(Collectors.toList());
		return userDtoList;
	}

	@Override
	public void deleteUser(Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		userRepository.delete(user);
	}

}
