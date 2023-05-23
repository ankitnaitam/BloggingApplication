package com.bikkadit.blog.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class MyConfiguration //implements CommandLineRunner
{

//	@Autowired
//	private PasswordEncoder passwordEncoder;
	
	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}

//	public void run(String... args) throws Exception {
//		System.out.println("Encoded Password :"+this.passwordEncoder.encode("Rahul121"));
//	}

}
