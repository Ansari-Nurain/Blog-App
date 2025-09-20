package com.blog_app_apis.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blog_app_apis.entities.User;
import com.blog_app_apis.exceptions.ResourceNotFoundException;
import com.blog_app_apis.repositries.UserRepository;

@Service
public class CustomeUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// find the user by the email from the database
		
		User user = repository.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("User", "email :  +username", 0));
		
		return user;
	}

}
