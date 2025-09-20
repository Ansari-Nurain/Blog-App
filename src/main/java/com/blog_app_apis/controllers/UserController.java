package com.blog_app_apis.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog_app_apis.payloads.ApiResponse;
import com.blog_app_apis.payloads.UserDto;
import com.blog_app_apis.services.UserServive;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserServive userServive;
	
	@PostMapping("/")
	public ResponseEntity<UserDto> createUserDto(@Valid @RequestBody UserDto userDto){
		
		UserDto createUserDto = this.userServive.createUser(userDto);
		
		return new ResponseEntity<>(createUserDto,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable Integer userId){
		
		UserDto updateUser = this.userServive.updateUser(userDto, userId);
		
		return ResponseEntity.ok(updateUser);
	}
	
//	@DeleteMapping("/{userId}")
//	public ResponseEntity<?> deleteUser(@PathVariable Integer userId){
//		
//		this.userServive.deleteUser(userId);
//		
////		return ResponseEntity.ok(Map.of("message", "user deleted successefully"));
//		
//		return new ResponseEntity(Map.of("message", "user deleted successfully"), HttpStatus.OK);
//		
//	}
	
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId){
		
		this.userServive.deleteUser(userId);		
		return new ResponseEntity<ApiResponse>(new ApiResponse("user deleted successfully", true), HttpStatus.OK);
		
	}
	
	@GetMapping
	public ResponseEntity<List<UserDto>> getAllUsers(){
		
		return ResponseEntity.ok(this.userServive.getAllUsers());
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUser(@PathVariable Integer userId){
		
		return ResponseEntity.ok(this.userServive.getById(userId));
	}

}
