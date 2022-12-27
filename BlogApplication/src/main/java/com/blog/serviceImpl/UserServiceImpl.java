package com.blog.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dto.UserDto;
import com.blog.entity.User;
import com.blog.exceptions.UserNotFoundException;
import com.blog.repositories.UserRepo;
import com.blog.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepo uRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		
		User user = this.dtoToUser(userDto);
		
		User savedUser = uRepo.save(user);
		
		return userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId)throws UserNotFoundException{
		 User  user = uRepo.findById(userId).orElseThrow(()-> new UserNotFoundException("User Not found.."));
		 
			user.setName(userDto.getName());
			user.setEmail(userDto.getEmail());
			user.setAbout(userDto.getAbout());
			user.setPassword(userDto.getPassword());
			
			User updatedUser = uRepo.save(user);
		 return userToDto(updatedUser);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		 User  user = uRepo.findById(userId).orElseThrow(()-> new UserNotFoundException("User Not found.."));
		
		 return userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = uRepo.findAll();
		List<UserDto> userDtos= users.stream().map(user->userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public String deleteUser(Integer userId) {
		User  user = uRepo.findById(userId).orElseThrow(()-> new UserNotFoundException("User Not found.."));
		
		uRepo.delete(user);
		return "User deleted successfully";
		
	}
	
	public User dtoToUser(UserDto userDto) {
		
		User user = modelMapper.map(userDto, User.class);
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setAbout(userDto.getAbout());
//		user.setPassword(userDto.getPassword());
		return user;
	}

	public UserDto userToDto(User user) {
		UserDto userDto = modelMapper.map(user, UserDto.class);
//	     userDto.setId(user.getId());
//	     userDto.setName(user.getName());
//	     userDto.setEmail(user.getEmail());
//	     userDto.setAbout(user.getAbout());
//	     userDto.setPassword(user.getPassword());
	     return userDto;
	}
}
