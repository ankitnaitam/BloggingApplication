package com.bikkadit.blog.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

	private Integer categoryId;
	
	@NotBlank
	@Size(min = 5,max = 15,message = "Title should have min 5 characters and max 15 characters")
	private String categoryTitle;
	
	@NotBlank
	@Size(min = 10,message = "should contain min 10 characters")
	private String categoryDescription;
	
	
}
