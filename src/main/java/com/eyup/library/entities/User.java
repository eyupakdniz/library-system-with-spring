package com.eyup.library.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="username", unique=true)
	private String username;
	
	@Column(name="password",nullable = false)
	private String password;
	
	@Column(name = "firstName", length = 100, nullable = false)
	private String firstName;
	
	@Column(name = "lastName", length = 100, nullable = false)
	private String lastName;
	
	@Column(name = "email", length = 100, nullable = false,unique=true)
	private String email;
	
	@OneToOne(mappedBy="user",
			cascade= {CascadeType.DETACH, CascadeType.MERGE,
					CascadeType.PERSIST, CascadeType.REFRESH},
			fetch=FetchType.LAZY)
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	private Reserved reserved;

}
