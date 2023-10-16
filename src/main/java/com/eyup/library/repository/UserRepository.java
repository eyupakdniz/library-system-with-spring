package com.eyup.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eyup.library.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
