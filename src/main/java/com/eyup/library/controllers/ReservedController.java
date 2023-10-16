package com.eyup.library.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eyup.library.entities.Book;
import com.eyup.library.entities.Reserved;
import com.eyup.library.request.ReservedCreateRequest;
import com.eyup.library.service.ReservedService;

@RestController
@RequestMapping("/reserved")
public class ReservedController {

	private ReservedService reservedService;

	public ReservedController(ReservedService reservedService) {
		this.reservedService = reservedService;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Reserved>> findAllReserved(){
		return new ResponseEntity<List<Reserved>>(reservedService.findAllReserved(), HttpStatus.OK);
	}
	
	@GetMapping("/receivable")
	public ResponseEntity<List<Book>> findAllreceivableBooks(){
		return new ResponseEntity<>(reservedService.findAllreceivableBooks(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Reserved> findReservedById(@PathVariable Long id){
		return new ResponseEntity<>(reservedService.findReservedById(id), HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<Reserved> createReserved(@RequestBody ReservedCreateRequest reservedCreateRequest){
		return new ResponseEntity<Reserved>(reservedService.createReserved(reservedCreateRequest),HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteReservedById(@PathVariable Long id) {
		reservedService.deleteBookById(id);
		return ResponseEntity.ok().build();
	}
}
