package com.eyup.library.dto;

import java.util.List;

import com.eyup.library.entities.Author;
import com.eyup.library.entities.Category;

import lombok.Data;

@Data
public class BookDto {

	private Long name;
	private String isbn;
	private String description;
	private Integer numberOfpages;
	private List<Author> authors;
	private List<Category> bookCategories;
}
