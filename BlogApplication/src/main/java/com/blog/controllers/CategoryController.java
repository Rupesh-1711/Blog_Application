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

import com.blog.dto.CategoryDto;
import com.blog.exceptions.CategoryException;
import com.blog.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService cService;
	
	@PostMapping("/")
	 public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto)throws CategoryException{
		 CategoryDto createdCategory = cService.createCategory(categoryDto);
		 
		 return new ResponseEntity<CategoryDto>(createdCategory,HttpStatus.CREATED);
	 }

	@PutMapping("/{categoryId}")
	 public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable("categoryId") Integer categoryId)throws CategoryException{
		 CategoryDto updateCategory = cService.updateCategory(categoryDto, categoryId);
		 
		 return new ResponseEntity<CategoryDto>(updateCategory,HttpStatus.CREATED);
	 }
	 
	@DeleteMapping("/{categoryId}")
	 public ResponseEntity<String> deleteCategory(@PathVariable("categoryId") Integer categoryId)throws CategoryException{
		 String deleteCategory = cService.deleteCategory(categoryId);
		 
		 return new ResponseEntity<String>(deleteCategory,HttpStatus.CREATED);
	 }
	
	@GetMapping("/{categoryId}")
	 public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("categoryId") Integer categoryId)throws CategoryException{
		 CategoryDto CategoryById = cService.getCategoryById(categoryId);
		 
		 return new ResponseEntity<CategoryDto>(CategoryById,HttpStatus.CREATED);
	 }
	 
	@GetMapping("/")
	 public ResponseEntity<List<CategoryDto>>  getAllCategory()throws CategoryException{
		 List<CategoryDto>allCategory = cService.getAllCategory();
		 
		 return new ResponseEntity<List<CategoryDto>>(allCategory,HttpStatus.CREATED);
	 }
}
