package com.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserConfig {

    @Bean
    UserDetailsService userDetailService() {
		UserDetails user1 = User.builder().username("kushagra")
				.password(passwordEncoder().encode("password")).roles("ADMIN").build();
		UserDetails user2 = User.builder().username("kartik")
				.password(passwordEncoder().encode("password1")).roles("ADMIN").build();
		return new InMemoryUserDetailsManager(user1, user2);
	}
    
    @Bean
    PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
}
