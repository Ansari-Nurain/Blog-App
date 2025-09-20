package com.blog_app_apis.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {

	private int id;
	
	@NotEmpty
	@Size(min = 3, message = "username must be greater than 3 characters")
	private String name;
	
	@Email(message = "your email is not valid")
	private String email;
	
	@NotEmpty
	@Size(min = 3, max = 8, message = "password must be minimum 3 and maximum 8 chars")
//	@Pattern(regexp = "?=.*?[A-Z]")
	private String password;
	
	@NotEmpty
	private String about;

}
