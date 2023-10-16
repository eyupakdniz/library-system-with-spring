package com.eyup.library.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.eyup.library.entities.Category;
import com.eyup.library.repository.CategoryRepository;
import com.eyup.library.request.CategoryCreateRequest;

@Service
public class CategoryService {

	private CategoryRepository categoryRepository;

	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	public List<Category> findAllCategories() {
		return categoryRepository.findAll();
	}
	
	public Category findCategoryById(Long id){
		return categoryRepository.findById(id).orElse(null);
	}
	
	public Category createCategory(CategoryCreateRequest categoryCreateRequest) {
		
		Category category = new Category();
		category.setName(categoryCreateRequest.getName());
		return categoryRepository.save(category);
	}
	
	public void deleteCategoryById(Long id) {
		Optional<Category> foundCategory = categoryRepository.findById(id);
		if (foundCategory.isPresent()) {
			Category category = foundCategory.get();
			categoryRepository.delete(category);
		} else {
			new RuntimeException(id + " not found.");
		}
		
	}
	
}
