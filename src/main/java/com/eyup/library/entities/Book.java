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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="books")
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", length = 100, nullable = false)
	private String name;
	
	@Column(name = "isbn", length = 50, nullable = false, unique = true)
	private String isbn;
	
	@Column(name = "description", length = 250, nullable = false)
	private String description;
	
	@Column(name = "numberOfpages", nullable = false)
	private Integer numberOfpages;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
	@JoinTable(name = "books_authors", joinColumns = { @JoinColumn(name = "book_id") }, inverseJoinColumns = {
			@JoinColumn(name = "author_id") })
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	private Author author;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "books_categories", joinColumns = { @JoinColumn(name = "book_id") }, inverseJoinColumns = {
			@JoinColumn(name = "category_id") })
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	private Set<Category> categories = new HashSet<Category>();
	
	@OneToOne(mappedBy="book",
			cascade= {CascadeType.DETACH, CascadeType.MERGE,
					CascadeType.PERSIST, CascadeType.REFRESH},
			fetch=FetchType.LAZY)
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	private Reserved reserved;
	
}
