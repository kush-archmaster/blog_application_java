package com.blog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.dtos.UserDto;
import com.blog.services.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	@Autowired
	UserService userService;
	
	/*
	 * Create a new user
	 */
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userReq) {
		 UserDto userDto = userService.createUser(userReq);
		 return new ResponseEntity<UserDto>(userDto, HttpStatus.CREATED);
	}
	
	/*
	 * Update information of existing user
	 */
	@PatchMapping(path = "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDto> updateUser(@PathVariable String userId, @RequestBody UserDto userReq) {
		 UserDto updateUser = userService.updateUser(userReq, Long.parseLong(userId));
		 return new ResponseEntity<>(updateUser, HttpStatus.OK);
	}
	
	/*
	 * Get user details based on userId
	 */
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable String userId) {
		 UserDto userDto = userService.getUserById(Long.parseLong(userId));
		 return new ResponseEntity<>(userDto, HttpStatus.OK);
	}
	
	/*
	 * Get all users
	 */
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		 List<UserDto> allUsers = userService.getAllUsers();
		 return new ResponseEntity<>(allUsers, HttpStatus.OK);
	}
	
	/*
	 * Delete a user
	 */
	@DeleteMapping(path = "/{userId}")
	public ResponseEntity<Map<String, String>> deleteUserById(@PathVariable String userId) {
		userService.deleteUser(Long.parseLong(userId));
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", "Deleted successfully");
		return new ResponseEntity<>(map, HttpStatus.NO_CONTENT);
	}
}
