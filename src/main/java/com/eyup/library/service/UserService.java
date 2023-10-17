package com.eyup.library.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eyup.library.entities.User;
import com.eyup.library.exception.UserNotFoundException;
import com.eyup.library.repository.UserRepository;
import com.eyup.library.request.UserCreateRequest;
import com.eyup.library.request.UserUpdateRequest;

@Service
public class UserService {

	private UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<User> getAllUser() {
		return userRepository.findAll();
	}
	
	public User getOneUser(Long id) {
		return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User Not Found ID : " + id));
	}

	public User createUser(UserCreateRequest userCreate) {
		User user = new User();
		user.setUsername(userCreate.getUsername());
		user.setPassword(userCreate.getPassword());
		user.setEmail(userCreate.getEmail());
		user.setFirstName(userCreate.getFirstName());
		user.setLastName(userCreate.getLastName());
		return userRepository.save(user);
	}

	public User updateUser(UserUpdateRequest userUpdate, Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User Not Found ID : " + id));
		
		user.setFirstName(userUpdate.getFirstName());
		user.setLastName(userUpdate.getLastName());
		user.setEmail(userUpdate.getEmail());
		user.setPassword(userUpdate.getPassword());
		user.setUsername(userUpdate.getUsername());
		userRepository.save(user);
		return user;
	}

	public void deleteUserById(Long id) {
		User userOptional = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User Not Found ID : " + id));
		userRepository.delete(userOptional);
	}

	

}
