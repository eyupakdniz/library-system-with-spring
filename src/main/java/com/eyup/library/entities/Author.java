package com.eyup.library.entities;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="authors")
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "firstName", length = 100, nullable = false)
	private String firstName;
	
	@Column(name = "lastName", length = 100, nullable = false)
	private String lastName;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE , CascadeType.REMOVE}, mappedBy = "author")
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	private Set<Book> books = new HashSet<Book>();
	
}
