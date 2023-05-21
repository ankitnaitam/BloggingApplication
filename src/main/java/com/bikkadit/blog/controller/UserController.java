package com.bikkadit.blog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.bikkadit.blog.payloads.UserDto;
import com.bikkadit.blog.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(value = ApiConstants.BASE_URL_USER)
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * @author Ankit
	 * @apiNote This Api is to save user data
	 * @param userDto
	 * @return
	 */
	@PostMapping()
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		log.info("Initiated request for save the User details");
		UserDto createUserDto = this.userService.createUser(userDto);
		log.info("Completed request for save the User details");
		return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
	}

	/**
	 * @author Ankit
	 * @apiNote This Api is to update user data
	 * @param userDto
	 * @param userId
	 * @return
	 */
	@PutMapping(value = ApiConstants.USER_ID)
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,
			@PathVariable("userId") Integer userId) {
		log.info("Initiated request for update the User details with userId:{}", userId);
		UserDto updateUser = this.userService.updateUser(userDto, userId);
		log.info("Completed request for update the User details with userId:{}", userId);
		return new ResponseEntity<>(updateUser, HttpStatus.OK);
	}

	/**
	 * @author Ankit
	 * @apiNote This Api is to get single user data
	 * @param userId
	 * @return
	 */
	@GetMapping(value = ApiConstants.USER_ID)
	public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId) {
		log.info("Initiated request to get the User details with userId:{}", userId);
		return ResponseEntity.ok(this.userService.getUserById(userId));
	}

	/**
	 * @author Ankit
	 * @apiNote This Api is to get all user data
	 * @return
	 */
	@GetMapping()
	public ResponseEntity<List<UserDto>> getAllUser() {
		log.info("Initiated request to get all User details");
		return ResponseEntity.ok(this.userService.getAllUsers());
	}

	/**
	 * @author Ankit
	 * @apiNote This Api is to delete user data
	 * @param userId
	 * @return
	 */
	@PreAuthorize(AppConstants.ROLE_ADMIN)
	@DeleteMapping(value = ApiConstants.USER_ID)
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer userId) {
		log.info("Initiated request for delete the User details with userId:{}", userId);
		this.userService.deleteUser(userId);
		log.info("Completed request for delete the User details with userId:{}", userId);
		return new ResponseEntity<>(new ApiResponse(AppConstants.USER_DELETE + userId, true), HttpStatus.OK);
	}

}
