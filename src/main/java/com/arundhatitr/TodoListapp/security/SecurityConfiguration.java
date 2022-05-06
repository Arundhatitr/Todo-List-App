package com.arundhatitr.TodoListapp.security;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.arundhatitr.TodoListapp.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Resource
	DataSource dataSource;

	@Resource
	UserService userService;

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/*h2-console**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/", "/*task*/**").access("hasAnyRole('ROLE_USER','ROLE_ADMIN')").and().formLogin()
				.loginPage("/login").failureUrl("/login?error").usernameParameter("username")
				.passwordParameter("password").and().logout().logoutSuccessUrl("/login?logout");
		http.headers().frameOptions().disable();
		http.httpBasic();
		http.csrf().disable();

	}
}