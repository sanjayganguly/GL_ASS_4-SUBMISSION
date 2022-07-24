package com.gl.employeesapi.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gl.employeesapi.model.DomainUserDetails;
import com.gl.employeesapi.model.User;
import com.gl.employeesapi.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DomainUserDetailsService implements UserDetailsService {
	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		User user = this.userRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("Invalid User"));
		
		 return new DomainUserDetails(user);
	}
	
	
}
