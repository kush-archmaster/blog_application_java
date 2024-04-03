package com.blog.security;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.blog.constants.BlogApplicationConstant;
import com.blog.exception.JwtException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	private JwtHelper jwtUtil;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
	
	/*
	 * whenever API is hit, this is the first layer before reaching the endpoint
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String username=null, token=null;
		String authHeaderToken = request.getHeader(BlogApplicationConstant.AUTH);
		if(authHeaderToken!=null && authHeaderToken.startsWith(BlogApplicationConstant.PREFIX_JWT_TOKEN)) {
			token = authHeaderToken.substring(7);
			try {
				username = jwtUtil.getUsernameFromToken(token);
			} catch (Exception e) {
				e.printStackTrace();
				throw new JwtException(String.format("Cannot authenticate user, msg: %s", e.getMessage()));
			}
		}
		
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			// if have to fetch from db and set -- create custom user details service -- CustomUserDetailService
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);  
			if(jwtUtil.validateToken(token, userDetails)) {
				JwtAuthenticationToken authentication = new JwtAuthenticationToken(userDetails);
				authentication.setAuthenticated(true);
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
			else {
				throw new JwtException("Invalid Jwt Token, Please check if token is passed properly.");
			}
		}
		
		filterChain.doFilter(request, response);
	}

}
