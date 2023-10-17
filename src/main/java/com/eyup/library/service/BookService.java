package com.eyup.library.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eyup.library.entities.Author;
import com.eyup.library.entities.Book;
import com.eyup.library.exception.BookNotFoundException;
import com.eyup.library.repository.BookRepository;
import com.eyup.library.request.BookCreateRequest;
import com.eyup.library.request.BookUpdateRequest;

@Service
public class BookService {
	
	private BookRepository bookRepository;
	private AuthorService authorService;

	public BookService(BookRepository bookRepository, AuthorService authorService) {
		this.bookRepository = bookRepository;
		this.authorService = authorService;
	}

	public List<Book> findAllBooks() {
		return bookRepository.findAll();
	}

	public Book findBookById(Long id) {
		return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book Not Found ID : " + id));
	}
/*
	public Book searchBook(Long id) {
		return null;
	}
*/
	public Book createBook(BookCreateRequest createBook) {
		Author author = authorService.findAuthorByName(createBook.getAuthorFirstName(),createBook.getAuthorLastName());
		Book book = new Book();
		book.setName(createBook.getName());
		book.setDescription(createBook.getDescription());
		book.setIsbn(createBook.getIsbn());
		book.setNumberOfpages(createBook.getNumberOfpages());
		book.setAuthor(author);
		return bookRepository.save(book);
	}

	public Book updateBook(BookUpdateRequest updateBook, Long id) {
		Author author = authorService.findAuthorByName(updateBook.getAuthorFirstName(),updateBook.getAuthorLastName());
		Book found= bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book Not Found ID : " + id));
		found.setName(updateBook.getName());
		found.setDescription(updateBook.getDescription());
		found.setIsbn(updateBook.getIsbn());
		found.setNumberOfpages(updateBook.getNumberOfpages());
		found.setAuthor(author);
		return bookRepository.save(found);

	}

	public void deleteBookById(Long id) {
		Book foundbook = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book Not Found ID : " + id));
		bookRepository.delete(foundbook);
		
	}

	
}





















