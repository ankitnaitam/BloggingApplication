package com.bikkadit.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bikkadit.blog.entities.Role;
import com.bikkadit.blog.entities.User;
import com.bikkadit.blog.exceptions.ResourceNotFoundException;
import com.bikkadit.blog.helper.AppConstants;
import com.bikkadit.blog.payloads.UserDto;
import com.bikkadit.blog.repositories.RoleRepo;
import com.bikkadit.blog.repositories.UserRepo;
import com.bikkadit.blog.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;

	/**
	 * @author Ankit
	 * @implNote This implementation is to save user data
	 */
	@Override
	public UserDto registerNewUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		// encode the password
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		// roles
		Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();
		user.getRoles().add(role);
		User newUser = this.userRepo.save(user);
		return this.modelMapper.map(newUser, UserDto.class);
	}

	@Override
	public UserDto createUser(UserDto userDto) {
		log.info("Initiated dao call for save the User details");
		User user = this.modelMapper.map(userDto, User.class);
		User savedUser = this.userRepo.save(user);
		log.info("Completed dao call for save the User details");
		return this.modelMapper.map(savedUser, UserDto.class);
	}

	/**
	 * @author Ankit
	 * @implNote This implementation is to update user data
	 */
	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		log.info("Initiated dao call for update the User details with userId:{}", userId);
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstants.USER_NOT_FOUND + userId));

		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		User updatedUser = this.userRepo.save(user);
		log.info("Completed dao call for update the User details with userId:{}", userId);
		return this.modelMapper.map(updatedUser, UserDto.class);
	}

	/**
	 * @author Ankit
	 * @implNote This implementation is to get single user data
	 */
	@Override
	public UserDto getUserById(Integer userId) {
		log.info("Initiated dao call to get the User details with userId:{}", userId);
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstants.USER_NOT_FOUND + userId));
		log.info("Completed dao call to get the User details with userId:{}", userId);
		return this.modelMapper.map(user, UserDto.class);
	}

	/**
	 * @author Ankit
	 * @implNote This implementation is to get all user data
	 */
	@Override
	public List<UserDto> getAllUsers() {
		log.info("Initiated dao call to get all User details");
		List<User> users = this.userRepo.findAll();
		List<UserDto> userDtos = users.stream().map(user -> this.modelMapper.map(user, UserDto.class))
				.collect(Collectors.toList());
		log.info("Completed dao call to get all User details");
		return userDtos;
	}

	/**
	 * @author Ankit
	 * @implNote This implementation is to delete user data
	 */
	@Override
	public void deleteUser(Integer userId) {
		log.info("Initiated dao call for delete the User details with userId:{}", userId);
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstants.USER_NOT_FOUND + userId));
		log.info("Completed dao call for delete the User details with userId:{}", userId);
		this.userRepo.delete(user);
	}

//	private User dtoToUser(UserDto userDto) {
//		User user = new User();
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
//		return user;
//	}
//
//	private UserDto userToDto(User user) {
//		UserDto userDto = new UserDto();
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
//		return userDto;
//	}

}
