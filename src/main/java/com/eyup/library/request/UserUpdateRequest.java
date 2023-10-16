package com.eyup.library.request;

import lombok.Data;

@Data
public class UserUpdateRequest {

	private String username;
	
	private String password;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
}
