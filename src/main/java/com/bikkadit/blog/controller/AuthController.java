package com.bikkadit.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bikkadit.blog.exceptions.ApiException;
import com.bikkadit.blog.helper.ApiConstants;
import com.bikkadit.blog.payloads.JwtAuthRequest;
import com.bikkadit.blog.payloads.UserDto;
import com.bikkadit.blog.security.JwtAuthResponse;
import com.bikkadit.blog.security.JwtTokenHelper;
import com.bikkadit.blog.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = ApiConstants.BASE_URL_AUTH)
@Slf4j
public class AuthController {

	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	/**
	 * @author Ankit
	 * @apiNote This Api is to create token
	 * @return response token
	 */
	@PostMapping(value = ApiConstants.LOGIN_USER)
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception {
		log.info("Initiated request for create token with username:{}", request.getUsername());

		this.authenticate(request.getUsername(), request.getPassword());

		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
		String token = this.jwtTokenHelper.generateToken(userDetails);

		JwtAuthResponse response = new JwtAuthResponse();
		response.setToken(token);

		log.info("Completed request for create token with username:{}", request.getUsername());
		return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
	}

	private void authenticate(String username, String password) throws Exception {

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
				password);
		try {
			this.authenticationManager.authenticate(authenticationToken);
		} catch (BadCredentialsException e) {
			System.out.println(ApiConstants.INVALID_MSG);
			throw new ApiException(ApiConstants.BAD_CREDENTIAL);
		}
	}

	/**
	 * @author Ankit
	 * @apiNote This Api is to register user
	 * @param userDto
	 * @return registeredUser
	 */
	// Register new user api
	@PostMapping(value = ApiConstants.REGISTER_USER)
	public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto) {
		log.info("Initiated request for register user with userId:{}",userDto.getId());
		UserDto registeredUser = this.userService.registerNewUser(userDto);
		log.info("Completed request for register user with userId:{}",userDto.getId());
		return new ResponseEntity<UserDto>(registeredUser, HttpStatus.CREATED);
	}

}
