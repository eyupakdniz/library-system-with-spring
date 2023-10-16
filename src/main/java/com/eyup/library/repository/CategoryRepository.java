package com.eyup.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eyup.library.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
