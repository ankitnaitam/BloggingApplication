package com.bikkadit.blog.controller;

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

import com.bikkadit.blog.helper.ApiConstants;
import com.bikkadit.blog.helper.AppConstants;
import com.bikkadit.blog.payloads.ApiResponse;
import com.bikkadit.blog.payloads.CategoryDto;
import com.bikkadit.blog.service.CategoryService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(value = ApiConstants.BASE_URL_CATEGORY)
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	/**
	 * @author Ankit
	 * @apiNote This Api is to save the category data
	 * @param categoryDto
	 * @return
	 */
	@PostMapping()
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
		log.info("Initiated request for save the Category details");
		CategoryDto createCategory = this.categoryService.createCategory(categoryDto);
		log.info("Completed request for save the Category details");
		return new ResponseEntity<CategoryDto>(createCategory, HttpStatus.CREATED);
	}

	/**
	 * @author Ankit
	 * @apiNote This Api is to update category data
	 * @param categoryDto
	 * @param categoryId
	 * @return
	 */
	@PutMapping(value = ApiConstants.CAT_ID)
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,
			@PathVariable Integer categoryId) {
		log.info("Initiated request for update the Category details with categoryId:{}", categoryId);
		CategoryDto updateCategory = this.categoryService.updateCategory(categoryDto, categoryId);
		log.info("Completed request for update the Category details with categoryId:{}", categoryId);
		return new ResponseEntity<CategoryDto>(updateCategory, HttpStatus.OK);
	}

	/**
	 * @author Ankit
	 * @apiNote This Api is to delete category data
	 * @param categoryId
	 * @return
	 */
	@DeleteMapping(value = ApiConstants.CAT_ID)
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId) {
		log.info("Initiated request for delete the Category details with categoryId:{}", categoryId);
		this.categoryService.deleteCategory(categoryId);
		log.info("Completed details for delete the Category details with categoryId:{}", categoryId);
		return new ResponseEntity<>(new ApiResponse(AppConstants.CATEGORY_DELETE + categoryId, true), HttpStatus.OK);
	}

	/**
	 * @author Ankit
	 * @apiNote This Api is to get single category data
	 * @param categoryId
	 * @return
	 */
	@GetMapping(value = ApiConstants.CAT_ID)
	public ResponseEntity<CategoryDto> getCategory(@Valid @PathVariable Integer categoryId) {
		log.info("Initiated request to get the Category details with categoryId:{}", categoryId);
		CategoryDto getCategory = this.categoryService.getCategory(categoryId);
		log.info("Completed request to get the Category details with categoryId:{}", categoryId);
		return new ResponseEntity<CategoryDto>(getCategory, HttpStatus.FOUND);
	}

	/**
	 * @author Ankit
	 * @apiNote This Api is to get All category data
	 * @return
	 */
	@GetMapping()
	public ResponseEntity<List<CategoryDto>> getCategories() {
		log.info("Initiated request to get all Category details");
		List<CategoryDto> allCategory = this.categoryService.getCategories();
		log.info("Completed request to get all Category details");
		return ResponseEntity.ok(allCategory);
	}
}
