package com.blog.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtAuthenticationToken extends AbstractAuthenticationToken{
	
	private static final long serialVersionUID = 1L;
	private UserDetails principal;
	
	public JwtAuthenticationToken(UserDetails principal) {
		super(principal.getAuthorities());
		this.principal = principal;
	}

	@Override
	public Object getPrincipal() {
		return principal;
	}
	
	@Override
	public Object getCredentials() {
		return null;
	}
}
