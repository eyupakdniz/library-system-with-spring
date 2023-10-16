package com.eyup.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eyup.library.entities.Reserved;

public interface ReservedRepository extends JpaRepository<Reserved, Long> {

}
