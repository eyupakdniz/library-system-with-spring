package com.eyup.library.request;


import lombok.Data;

@Data
public class BookCreateRequest {

	private String name;
	
	private String isbn;
	
	private String description;

	private Integer numberOfpages;
	
	//private Set<Category> Categories;
	
	private String authorFirstName;
	
	private String authorLastName;
}
