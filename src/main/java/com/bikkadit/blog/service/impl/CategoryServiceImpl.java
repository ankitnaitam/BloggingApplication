package com.bikkadit.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bikkadit.blog.entities.Category;
import com.bikkadit.blog.exceptions.ResourceNotFoundException;
import com.bikkadit.blog.payloads.CategoryDto;
import com.bikkadit.blog.repositories.CategoryRepo;
import com.bikkadit.blog.service.CategoryService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo catRepo;

	@Autowired
	private ModelMapper modelMapper;

	/**
	 * @author Ankit
	 * @implNote This implementation is to save category data
	 */
	@Override
	public CategoryDto createCategory(CategoryDto catDto) {
		log.info("Initiated dao call for save the Category details");
		Category category = this.modelMapper.map(catDto, Category.class);
		Category savedCategory = this.catRepo.save(category);
		log.info("Completed dao call for save the Category details");
		return this.modelMapper.map(savedCategory, CategoryDto.class);
	}

	/**
	 * @author Ankit
	 * @implNote This implementation is to update category data
	 */
	@Override
	public CategoryDto updateCategory(CategoryDto catDto, Integer categoryId) {
		log.info("Initiated dao call for update the Category details with categoryId:{}", categoryId);
		Category category = this.catRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category ", "Category Id ", categoryId));

		category.setCategoryTitle(catDto.getCategoryTitle());
		category.setCategoryDescription(catDto.getCategoryDescription());
		Category updatedCategory = this.catRepo.save(category);
		log.info("Completed dao call for update the Category details with categoryId:{}", categoryId);
		return this.modelMapper.map(updatedCategory, CategoryDto.class);
	}

	/**
	 * @author Ankit
	 * @implNote This implementation is to delete category data
	 */
	@Override
	public void deleteCategory(Integer categoryId) {
		log.info("Initiated dao call for delete the Category details with categoryId:{}", categoryId);
		Category category = this.catRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category ", "Category Id ", categoryId));
		log.info("Completed dao call for delete the Category details with categoryId:{}", categoryId);
		this.catRepo.delete(category);
	}

	/**
	 * @author Ankit
	 * @implNote This implementation is to get single category data
	 */
	@Override
	public CategoryDto getCategory(Integer categoryId) {
		log.info("Initiated dao call to get the Category details with categoryId:{}", categoryId);
		Category category = this.catRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category ", "Category Id ", categoryId));
		log.info("Completed dao call to get the Category details with categoryId:{}", categoryId);
		return this.modelMapper.map(category, CategoryDto.class);
	}

	/**
	 * @author Ankit
	 * @implNote This implementation is to get all category data
	 */
	@Override
	public List<CategoryDto> getCategories() {
		log.info("Initiated dao call to get all Category details");
		List<Category> categories = this.catRepo.findAll();
		List<CategoryDto> catDtos = categories.stream().map((cat) -> this.modelMapper.map(cat, CategoryDto.class))
				.collect(Collectors.toList());
		log.info("Completed dao call to get all Category details");
		return catDtos;
	}

}
