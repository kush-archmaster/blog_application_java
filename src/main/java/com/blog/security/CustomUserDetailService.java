//package com.blog.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import com.blog.constants.BlogApplicationConstant;
//import com.blog.entities.User;
//import com.blog.exception.ResourceNotFoundException;
//import com.blog.repositories.UserRepository;
//
//public class CustomUserDetailService implements UserDetailsService {
//
//	@Autowired
//	private UserRepository userRepo;
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		User user = userRepo.findByEmail(username)
//				.orElseThrow(() -> new ResourceNotFoundException(BlogApplicationConstant.USER, BlogApplicationConstant.EMAIL + username, 0l));
//		return user;
//	}
//
//}
