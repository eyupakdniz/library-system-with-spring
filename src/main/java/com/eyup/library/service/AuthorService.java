package com.eyup.library.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eyup.library.entities.Author;
import com.eyup.library.exception.AuthorNotFoundException;
import com.eyup.library.repository.AuthorRepository;
import com.eyup.library.request.AuthorCreateRequest;
import com.eyup.library.request.AuthorUpdateRequest;

@Service
public class AuthorService {
	
	private AuthorRepository authorRepository;


	public AuthorService(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}

	public List<Author> findAllAuthors() {
		return authorRepository.findAll();
	}

	public Author findAuthorById(Long id) {
		return authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException("Author Not Found ID : " + id));
	}
	
	public Author findAuthorByName(String firstName, String lastName) {
		Author first = authorRepository.findByFirstName(firstName);
		Author last = authorRepository.findByLastName(lastName);
		if(first!= null && last != null && first.equals(last)) {
			return first;
		}else {
			Author author = new Author();
			author.setFirstName(firstName);
			author.setLastName(lastName);
			return authorRepository.save(author);
		}
	}

	public Author createAuthor(AuthorCreateRequest createAuthor) {
		Author author = new Author();
		author.setFirstName(createAuthor.getFirstName());
		author.setLastName(createAuthor.getLastName());
		return authorRepository.save(author);
	}

	public Author updateAuthor(AuthorUpdateRequest updateAuthor, Long id) {
		Author foundAuthor = authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException("Author Not Found ID : " + id));
		foundAuthor.setFirstName(updateAuthor.getFirstName());
		foundAuthor.setLastName(updateAuthor.getLastName());
		authorRepository.save(foundAuthor);
		return foundAuthor;
	}

	public void deleteAuthorById(Long id) {
		Author foundAuthor = authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException("Author Not Found ID : " + id));
		authorRepository.delete(foundAuthor);
		
	}

}
