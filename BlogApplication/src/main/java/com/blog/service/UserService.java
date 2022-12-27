package com.blog.service;

import java.util.List;

import com.blog.dto.UserDto;


public interface UserService {

	UserDto createUser(UserDto user);
	
	UserDto updateUser(UserDto user,Integer userId);
	
	UserDto getUserById(Integer userId);
	
	List<UserDto> getAllUsers();
	
	String deleteUser(Integer userId);
}
