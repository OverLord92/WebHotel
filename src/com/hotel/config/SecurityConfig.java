package com.hotel.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	public void confugureGlobal(AuthenticationManagerBuilder auth) throws Exception {		

		auth.jdbcAuthentication().passwordEncoder(passwordEncoder())
			.dataSource(dataSource)
			.usersByUsernameQuery(
					"SELECT username, password, enabled FROM user WHERE BINARY username=?")
			.authoritiesByUsernameQuery(
					"SELECT username, authority FROM user WHERE BINARY username=?");
		
	}
		
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		http
				.authorizeRequests()
				.antMatchers("/home").access("isAuthenticated()")
			.and()
				.formLogin()
				.defaultSuccessUrl("/add")
				.failureUrl("/login?loginerror=true")
			.and()
				.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/home");
		
	}
	
	@Bean
	public StandardPasswordEncoder passwordEncoder(){
		return new StandardPasswordEncoder();
	}
		
}