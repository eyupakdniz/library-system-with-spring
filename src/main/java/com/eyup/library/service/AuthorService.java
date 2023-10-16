package com.eyup.library.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.eyup.library.entities.Author;
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
		return authorRepository.findById(id).orElse(null);
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
		Optional<Author> foundAuthor = authorRepository.findById(id);
		if (foundAuthor.isPresent()) {
			Author author = foundAuthor.get();
			author.setFirstName(updateAuthor.getFirstName());
			author.setLastName(updateAuthor.getLastName());
			authorRepository.save(author);
			return author;
		}
		return null;
	}

	public void deleteAuthorById(Long id) {
		Optional<Author> foundAuthor = authorRepository.findById(id);
		if (foundAuthor.isPresent()) {
			Author author = foundAuthor.get();
			authorRepository.delete(author);
		} else {
			new RuntimeException(id + " not found.");
		}
		
	}

}
