package com.blog_app_apis.services;

import java.util.List;

import com.blog_app_apis.payloads.UserDto;

public interface UserServive {
	
	UserDto createUser(UserDto user);
	
	UserDto updateUser(UserDto user, Integer userId);
	
	UserDto getById(Integer userId);
	
	List<UserDto> getAllUsers();
	
	void deleteUser(Integer userId);
	
	

}
