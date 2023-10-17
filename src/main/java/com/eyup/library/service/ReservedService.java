package com.eyup.library.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.eyup.library.entities.Book;
import com.eyup.library.entities.Reserved;
import com.eyup.library.entities.User;
import com.eyup.library.exception.ReservedNotFoundException;
import com.eyup.library.repository.ReservedRepository;
import com.eyup.library.request.ReservedCreateRequest;

@Service
public class ReservedService {
	
	private ReservedRepository reservedRepository;
	private UserService userService;
	private BookService bookService;
	

	public ReservedService(ReservedRepository reservedRepository, UserService userService,
			BookService bookService) {
		this.reservedRepository = reservedRepository;
		this.userService = userService;
		this.bookService = bookService;
	}

	public List<Reserved> findAllReserved() {
		return reservedRepository.findAll();
	}

	public Reserved findReservedById(Long id) {
		return reservedRepository.findById(id).orElseThrow(() -> new ReservedNotFoundException("Reserved Not Found ID : " + id));
	}

	public Reserved createReserved(ReservedCreateRequest reservedCreateRequest) {
		Book book = bookService.findBookById(reservedCreateRequest.getBookId());
		User user = userService.getOneUser(reservedCreateRequest.getUserId());
		Reserved reserved = new Reserved();
		reserved.setBook(book);
		reserved.setUser(user);
		return reservedRepository.save(reserved);
	}

	public void deleteBookById(Long id) {
		Reserved found = reservedRepository.findById(id).orElseThrow(() -> new ReservedNotFoundException("Reserved Not Found ID : " + id));
			reservedRepository.delete(found);
		
	}
	
	public List<Book> findAllreceivableBooks() {
		List<Reserved> reservedBooks = reservedRepository.findAll();
        
	    List<Book> allBooks = bookService.findAllBooks();
	    
	    List<Long> reservedBookIds = reservedBooks.stream()
	        .map(reserved -> reserved.getBook().getId())
	        .collect(Collectors.toList());
	    
	    List<Book> availableBooks = allBooks.stream()
	        .filter(book -> !reservedBookIds.contains(book.getId()))
	        .collect(Collectors.toList());
	    
	    return availableBooks;
	}

	
}
