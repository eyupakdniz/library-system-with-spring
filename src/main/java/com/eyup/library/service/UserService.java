package com.eyup.library.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.eyup.library.entities.User;
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
		return userRepository.findById(id).orElse(null);
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
		Optional<User> user = userRepository.findById(id);
		if(user.isPresent()) {
			User found = user.get();
			found.setFirstName(userUpdate.getFirstName());
			found.setLastName(userUpdate.getLastName());
			found.setEmail(userUpdate.getEmail());
			found.setPassword(userUpdate.getPassword());
			found.setUsername(userUpdate.getUsername());
			userRepository.save(found);
			return found;
		}else {
			return null; 
		}
	}

	public void deleteUserById(Long id) {
		Optional<User> userOptional = userRepository.findById(id);
		if (userOptional.isPresent()) {
		    User user = userOptional.get();
		    userRepository.delete(user);
		} else {
			new RuntimeException(id + " not found.");
		}
		
	}

	

}
