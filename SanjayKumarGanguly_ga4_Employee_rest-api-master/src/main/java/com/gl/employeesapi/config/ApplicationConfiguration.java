package com.gl.employeesapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfiguration extends WebSecurityConfigurerAdapter {
	
	private final UserDetailsService userDetailsService;
	
	@Override
	 protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
	         //configure users
	         authenticationManagerBuilder
	                 .userDetailsService(this.userDetailsService)
	                 .passwordEncoder(bcryptPasswordEncoder());
	     }

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		// configure authorization rules here
		httpSecurity.cors().disable();
		httpSecurity.headers().frameOptions().disable();
		httpSecurity.csrf().disable();
		httpSecurity.authorizeRequests()
		  .antMatchers("/h2-console**", "/h2-console/**")
		  	.permitAll();

		httpSecurity.authorizeRequests().antMatchers(HttpMethod.GET, "/api/employees**").hasAnyRole("USER", "ADMIN")
				.and().authorizeRequests().antMatchers(HttpMethod.POST, "/api/employees**").hasRole("ADMIN")

				.antMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN").anyRequest().authenticated()

				.and().httpBasic().and()
				
				/*
				 * Set the sessioncreation policy to avoid using cookies for authentication
				 * https://stackoverflow.com/questions/50842258/spring-security-caching-my-
				 * authentication/50847571
				 */
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	}
	
	@Bean
	public PasswordEncoder bcryptPasswordEncoder() {
		
		return new BCryptPasswordEncoder();
	}

}
