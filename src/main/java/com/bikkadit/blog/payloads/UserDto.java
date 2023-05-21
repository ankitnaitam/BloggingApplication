package com.bikkadit.blog.payloads;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.bikkadit.blog.entities.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

	private Integer id;

	@NotEmpty
	@Size(min = 4, message = "username must contain min 4 characters !!")
	private String name;

	@Email(message = "Email address is not valid !!")
	private String email;

	@NotEmpty
	@Size(min = 4, max = 10, message = "Password must contain min 4 characters and max 10 characters !!")
	private String password;

	@NotEmpty
	private String about;
	
	private Set<Role> roles = new HashSet<>();
	
	private List<CommentDto> comments= new ArrayList<>();

}
