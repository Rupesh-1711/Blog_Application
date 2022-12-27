package com.blog.service;

import java.util.List;

import com.blog.dto.CategoryDto;
import com.blog.entity.Category;
import com.blog.exceptions.CategoryException;

public interface CategoryService {

	 public CategoryDto createCategory(CategoryDto categoryDto)throws CategoryException;

	 public CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId)throws CategoryException;
	 
	 public String deleteCategory(Integer categoryId)throws CategoryException;
	
	 public CategoryDto getCategoryById(Integer categoryId)throws CategoryException;
	 
	 public List<CategoryDto>  getAllCategory()throws CategoryException;
}
