package com.arundhatitr.TodoListapp.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class SecuredUser extends User implements UserDetails {
	private static final long serialVersionUID = 1L;

	public SecuredUser(Optional<User> user) {

		this.setUserId(user.get().getName());
		this.setName(user.get().getName());
		this.setPassword(user.get().getPassword());
		this.setRole(user.get().getRole());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		String userRole = super.getRole();
		if (userRole != null) {
			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole);
			authorities.add(authority);
		}
		return authorities;
	}

	@Override
	public String getUsername() {
		return super.getUserId();
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

}
