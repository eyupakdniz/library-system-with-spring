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

import com.eyup.library.entities.Author;
import com.eyup.library.request.AuthorCreateRequest;
import com.eyup.library.request.AuthorUpdateRequest;
import com.eyup.library.service.AuthorService;

@RestController
@RequestMapping("author")
public class AuthorController {

	private AuthorService authorService;

	public AuthorController(AuthorService authorService) {
		this.authorService = authorService;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Author>> findAllAuthors(){
		return new ResponseEntity<>(authorService.findAllAuthors(), HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Author> findAuthorById(@PathVariable Long id){
		return new ResponseEntity<>(authorService.findAuthorById(id), HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<Author> createAuthor(@RequestBody AuthorCreateRequest createBook){
		return new ResponseEntity<>(authorService.createAuthor(createBook), HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Author> updateAuthor(@RequestBody AuthorUpdateRequest updateBook,@PathVariable Long id){
		return new ResponseEntity<>(authorService.updateAuthor(updateBook,id), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteAuthorById(@PathVariable Long id) {
		authorService.deleteAuthorById(id);
		return ResponseEntity.ok().build();
	}
}
