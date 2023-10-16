package com.eyup.library.controllers;

import java.util.List;

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

import com.eyup.library.entities.User;
import com.eyup.library.request.UserCreateRequest;
import com.eyup.library.request.UserUpdateRequest;
import com.eyup.library.service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<User>> getAllUser(){
		return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<User> getOneUser(@PathVariable Long id){
		return new ResponseEntity<>(userService.getOneUser(id), HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<User> createUser(@RequestBody UserCreateRequest userCreate){
		return new ResponseEntity<>(userService.createUser(userCreate), HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<User> updateUser(@RequestBody UserUpdateRequest userUpdateDto, @PathVariable Long id){
		return new ResponseEntity<>(userService.updateUser(userUpdateDto,id), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
		userService.deleteUserById(id);
		return ResponseEntity.ok().build();
	}
}
