package com.bikkadit.blog.helper;

public class ApiConstants {
	
	//public url's
	public static final String[] PUBLIC_URL= {"/api/v1/auth/**",
												"/v3/api-docs",
												"/v2/api-docs", //swagger-ui will use this
												"/swagger-resources/**",
												"/swagger-ui/**",
												"/webjars/**"
											  };

	public static final String AUTHORIZATION_HEADER="Authorization";
	
	//Authentication - AuthController
	public static final String BASE_URL_AUTH = "/api/v1/auth/";
	public static final String LOGIN_USER = "/login";
	public static final String REGISTER_USER="/register";
	
	//authenticate constants
	public static final String INVALID_MSG="Invalid details !!";
	public static final String BAD_CREDENTIAL="Invalid username or password";
	
	//Category Api's
	public static final String BASE_URL_CATEGORY="/api/categories/";
	public static final String CAT_ID="/{categoryId}";
	
	//Comment Api's
	public static final String BASE_URL_COMMENT="/api/comments/";
	public static final String COM_ID="/{comId}";
	
	
	//Post Api's
	public static final String POST_ID="/{postId}";
	public static final String BASE_URL_POST="/api/posts/";
	public static final String POST_CREATE="/user/{userId}/category/{categoryId}";
	public static final String POSTS_GET_BY_USER="/user/{userId}";
	public static final String POSTS_GET_BY_CATEGORY="/category/{categoryId}";
	public static final String POSTS_SEARCH="/search/{keywords}";
	
	
	//User Api's
	public static final String BASE_URL_USER="api/users/";
	public static final String USER_ID="/{userId}";
	
}
