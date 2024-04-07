package com.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.dtos.JwtAuthRequest;
import com.blog.dtos.JwtAuthResponse;
import com.blog.dtos.UserDto;
import com.blog.exception.JwtException;
import com.blog.security.JwtHelper;
import com.blog.services.UserService;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtHelper jwtHelper;
	
	@PostMapping("/login") 
	public ResponseEntity<JwtAuthResponse> login(@RequestBody JwtAuthRequest authReq){
		/* check whether user is authenticated or not */
		doAuthenticate(authReq.getUsername(), authReq.getPassword());
		UserDetails userDetails = userDetailsService.loadUserByUsername(authReq.getUsername());
		String token = jwtHelper.generateToken(userDetails);
		
		JwtAuthResponse jwtResponse = JwtAuthResponse.builder().token(token).build();
		return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
	}
	
	private void doAuthenticate(String email, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
        	authenticationManager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new JwtException(" Invalid Username or Password  !!");
        }
    }
	
	@PostMapping("/register/user")
	public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto) {
		UserDto registeredUser = userService.registerUser(userDto);
		return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
	}
}
