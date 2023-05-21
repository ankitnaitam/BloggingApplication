package com.bikkadit.blog.service;

import java.util.List;

import com.bikkadit.blog.payloads.CategoryDto;

public interface CategoryService {
	
	CategoryDto createCategory(CategoryDto catDto);
	
	CategoryDto updateCategory(CategoryDto catDto,Integer categoryId);
	
	void deleteCategory(Integer categoryId);
	
	CategoryDto getCategory(Integer categoryId);
	
	List<CategoryDto> getCategories();
}
