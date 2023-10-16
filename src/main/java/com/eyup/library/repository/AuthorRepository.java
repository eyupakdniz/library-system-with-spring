package com.eyup.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eyup.library.entities.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
	 Author findByFirstName(String firstName);
	 Author findByLastName(String lastName);
}
