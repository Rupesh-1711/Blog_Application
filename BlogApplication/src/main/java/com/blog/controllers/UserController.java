package com.blog.controllers;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.dto.UserDto;
import com.blog.service.UserService;
import com.blog.serviceImpl.UserServiceImpl;

@RestController
@RequestMapping("/api/users")
public class UserController {


	@Autowired
	UserService uService;
	
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		UserDto createdUser = uService.createUser(userDto);
		return new ResponseEntity<UserDto>(createdUser, HttpStatus.CREATED);
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto user,@PathVariable("userId") Integer userId) {
		UserDto updatedUser = uService.updateUser(user, userId);
		return new ResponseEntity<UserDto>(updatedUser, HttpStatus.OK);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("userId") Integer userId) {
	     UserDto userById = uService.getUserById(userId);
		return new ResponseEntity<UserDto>(userById, HttpStatus.OK);
	}
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		List<UserDto> list = uService.getAllUsers();
		return new ResponseEntity<List<UserDto>>(list, HttpStatus.OK);
	}
	@DeleteMapping("/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable("userId") Integer userId) {
		String message = uService.deleteUser(userId);
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	
}
