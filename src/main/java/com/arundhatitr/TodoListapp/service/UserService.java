package com.arundhatitr.TodoListapp.service;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.arundhatitr.TodoListapp.model.SecuredUser;
import com.arundhatitr.TodoListapp.model.User;
import com.arundhatitr.TodoListapp.repository.UserRepository;

@Component
public class UserService implements UserDetailsService {

	@Resource
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findById(userId);

		return new SecuredUser(user);
	}
}
