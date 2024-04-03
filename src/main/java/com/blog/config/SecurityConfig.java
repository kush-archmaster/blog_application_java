package com.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.blog.security.JWTAuthenticationEntryPoint;
import com.blog.security.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {

	@Autowired
    private JWTAuthenticationEntryPoint point;
    @Autowired
    private JwtAuthenticationFilter filter;
    
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	// configuration
    	http.csrf(csrf -> csrf.disable())
    	.cors(cors -> cors.disable())
    	.authorizeHttpRequests(auth -> auth.requestMatchers("/api/v1/category/**", "/api/v1/comments/**", "/api/v1/user/**", "/api/v1/users/**", "/api/v1/post/**" ,"/api/v1/posts/**").authenticated()
    			.requestMatchers("/api/v1/auth/**").permitAll()
    			.anyRequest().authenticated())
    	.exceptionHandling(ex -> ex.authenticationEntryPoint(point))
    	.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    	
    	http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    	
    	return http.build();
    }
}
