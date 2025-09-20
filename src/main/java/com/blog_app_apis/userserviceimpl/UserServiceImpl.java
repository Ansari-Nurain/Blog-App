package com.blog_app_apis.userserviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog_app_apis.entities.User;
import com.blog_app_apis.exceptions.ResourceNotFoundException;
import com.blog_app_apis.payloads.UserDto;
import com.blog_app_apis.repositries.UserRepository;
import com.blog_app_apis.services.UserServive;

@Service
public class UserServiceImpl implements UserServive{
	
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ModelMapper mapper;
	
	public User dtoToUser(UserDto userDto) {
		
		User user = this.mapper.map(userDto, User.class);
		
//		User user = new User();
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
	
		return user;
	}
	
	public UserDto userToDto(User user) {
		
		UserDto userDto = this.mapper.map(user, UserDto.class);
		
//		UserDto userDto = new UserDto();
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
		
		return userDto;
	}
	
	
	@Override
	public UserDto createUser(UserDto userDto) {

		 User dtoToUser = this.dtoToUser(userDto);
		
		User savedUser = this.userRepo.save(dtoToUser);
		
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {

		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
	
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updatedUser = this.userRepo.save(user);
		
		UserDto updatedUser1 = userToDto(updatedUser);
		
		return updatedUser1;
	}

	@Override
	public UserDto getById(Integer userId) {

		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
		
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {

		List<User> users = this.userRepo.findAll();
		 
		List<UserDto> userDtos = users.stream().map(user-> this.userToDto(user)).collect(Collectors.toList());
		
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {

		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
		this.userRepo.delete(user);
	}

}
