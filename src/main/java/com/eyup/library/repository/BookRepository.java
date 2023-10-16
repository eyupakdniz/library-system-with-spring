package com.eyup.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eyup.library.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

}
