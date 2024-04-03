/*
 * For in memory users database implementation for jwt authentication
 */
package com.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {

//    @Bean
//    UserDetailsService userDetailService() {
//		UserDetails user1 = User.builder().username("kushagra")
//				.password(passwordEncoder().encode("password")).roles("ADMIN").build();
//		UserDetails user2 = User.builder().username("kartik")
//				.password(passwordEncoder().encode("password1")).roles("ADMIN").build();
//		return new InMemoryUserDetailsManager(user1, user2);
//	}
//    
	
	@Bean
    PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
	
	@Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }
}
