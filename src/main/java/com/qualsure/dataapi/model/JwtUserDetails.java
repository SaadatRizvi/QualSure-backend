package com.qualsure.dataapi.model;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@SuppressWarnings("serial")
public class JwtUserDetails implements UserDetails {

	private String userName;
	private long id;
	private String token;
	private Collection<? extends GrantedAuthority> authorities;
	

	public JwtUserDetails(String userName, long id, String token, Collection<? extends GrantedAuthority> grantedAuthorities) {
		this.userName = userName;
		this.id = id;
		this.token = token;
		this.authorities = grantedAuthorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return null;
	}

	 
	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getUserName() {
		return userName;
	}


	public long getId() {
		return id;
	}


	public String getToken() {
		return token;
	}



}
