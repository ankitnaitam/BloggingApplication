package com.bikkadit.blog.repositories;

import java.util.Optional; 
import org.springframework.data.jpa.repository.JpaRepository;
import com.bikkadit.blog.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{
	
	// method for db based authentication
	Optional<User> findByEmail(String email);

}
