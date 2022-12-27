package com.blog.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dto.CategoryDto;
import com.blog.entity.Category;
import com.blog.exceptions.CategoryException;
import com.blog.repositories.CategoryRepo;
import com.blog.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepo cRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) throws CategoryException {
		Category cat = modelMapper.map(categoryDto, Category.class);
		Category savedCat = cRepo.save(cat);
		
		return modelMapper.map(savedCat, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) throws CategoryException {
	    Category cat =  cRepo.findById(categoryId).orElseThrow(()->new CategoryException("Category not found With this Id"));
	      cat.setCategoryTitle(categoryDto.getCategoryTitle());
	      cat.setCategoryDescription(categoryDto.getCategoryDescription());
	      Category updatedCat = cRepo.save(cat);
	      
	      return modelMapper.map(updatedCat, CategoryDto.class);
	}

	@Override
	public String deleteCategory(Integer categoryId) throws CategoryException {
		 Category cat =  cRepo.findById(categoryId).orElseThrow(()->new CategoryException("Category not found With this Id"));
		        cRepo.delete(cat);
		 return "Category Deleted successfully....";
	}

	@Override
	public CategoryDto getCategoryById(Integer categoryId) throws CategoryException {
		 Category cat =  cRepo.findById(categoryId).orElseThrow(()->new CategoryException("Category not found With this Id"));
		return modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory() throws CategoryException {
		List<Category> categorys = cRepo.findAll();
		List<CategoryDto> categoryDtos =categorys.stream().map((cat)->modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		if(categoryDtos.size()==0) {
			throw new CategoryException("Categories not found.....");
		}
		return categoryDtos;
	}

}
