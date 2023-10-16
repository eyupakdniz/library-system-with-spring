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

import com.eyup.library.entities.Book;
import com.eyup.library.request.BookCreateRequest;
import com.eyup.library.request.BookUpdateRequest;
import com.eyup.library.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {

	
	private BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Book>> findAllBooks(){
		return new ResponseEntity<>(bookService.findAllBooks(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Book> findBookById(@PathVariable Long id){
		return new ResponseEntity<>(bookService.findBookById(id), HttpStatus.OK);
	}
	/*
	@GetMapping("/{searchBook}")
	public ResponseEntity<Book> searchBook(@PathVariable Long id){
		return new ResponseEntity<>(bookService.searchBook(id), HttpStatus.OK);
	}
	*/
	@PostMapping("/create")
	public ResponseEntity<Book> createBook(@RequestBody BookCreateRequest createBook){
		return new ResponseEntity<>(bookService.createBook(createBook), HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Book> updateBook(@RequestBody BookUpdateRequest updateBook,@PathVariable Long id){
		return new ResponseEntity<>(bookService.updateBook(updateBook,id), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteBookById(@PathVariable Long id) {
		bookService.deleteBookById(id);
		return ResponseEntity.ok().build();
	}
	
}
















