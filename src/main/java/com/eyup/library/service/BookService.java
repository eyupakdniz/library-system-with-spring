package com.eyup.library.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.eyup.library.entities.Author;
import com.eyup.library.entities.Book;
import com.eyup.library.entities.Reserved;
import com.eyup.library.repository.BookRepository;
import com.eyup.library.request.BookCreateRequest;
import com.eyup.library.request.BookUpdateRequest;

@Service
public class BookService {
	
	private BookRepository bookRepository;
	//private CategoryService categoryService;
	private AuthorService authorService;

	public BookService(BookRepository bookRepository, /*CategoryService categoryService,*/ AuthorService authorService) {
		this.bookRepository = bookRepository;
		//this.categoryService = categoryService;
		this.authorService = authorService;
	}

	public List<Book> findAllBooks() {
		return bookRepository.findAll();
	}

	public Book findBookById(Long id) {
		return bookRepository.findById(id).orElse(null);
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
		//book.setCategories(createBook.getCategories());
		return bookRepository.save(book);
	}

	public Book updateBook(BookUpdateRequest updateBook, Long id) {
		Author author = authorService.findAuthorByName(updateBook.getAuthorFirstName(),updateBook.getAuthorLastName());
		Optional<Book> found= bookRepository.findById(id);
		if(found.isPresent()) {
			Book book = found.get();
			book.setName(updateBook.getName());
			book.setDescription(updateBook.getDescription());
			book.setIsbn(updateBook.getIsbn());
			book.setNumberOfpages(updateBook.getNumberOfpages());
			book.setAuthor(author);
			return bookRepository.save(book);
		}
 		return null;
	}

	public void deleteBookById(Long id) {
		Optional<Book> foundbook = bookRepository.findById(id);
		if (foundbook.isPresent()) {
			Book book = foundbook.get();
			bookRepository.delete(book);
		} else {
			new RuntimeException(id + " silmek için bulunamadı.");
		}
		
	}

	
}





















