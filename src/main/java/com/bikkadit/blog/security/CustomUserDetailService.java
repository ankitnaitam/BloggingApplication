package com.bikkadit.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bikkadit.blog.entities.User;
import com.bikkadit.blog.exceptions.ResourceNotFoundException;
import com.bikkadit.blog.helper.AppConstants;
import com.bikkadit.blog.repositories.UserRepo;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		// loading user from db by username i.e. email ------authentication based on db
		// field values

		User user = this.userRepo.findByEmail(userName)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstants.USERNAME_NOT_FOUND+ userName));

		return user;
	}

}
