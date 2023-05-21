package com.bikkadit.blog.helper;

public class AppConstants {

	public static final String PAGE_NUMBER = "0";
	public static final String PAGE_SIZE = "5";
	public static final String SORT_BY = "postId";
	public static final String SORT_DIR = "asc";
	public static final Integer NORMAL_USER = 502;
	public static final Integer ADMIN_USER = 501;
	public static final String ROLE_ADMIN = "hasRole('ADMIN')";

	public static final String CATEGORY_DELETE = "Category deleted successfully with Id :";
	public static final String COMMENT_DELETE = "Comment deleted successfully with Id :";
	public static final String POST_DELETE = "Post deleted Successfully with Id :";
	public static final String USER_DELETE = "User deleted successfully with Id :";

	// JwtAuthenticationEntryPoint
	public static final String UNOTHORIZED = "Access Denied !!";

	// JwtAuthenticationFilter
	public static final String TOKEN_HEADER = "Authorization";
	public static final String TOKEN_START_WITH = "Bearer";

	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
	public static final String TOKEN_SECREAT_KEY = "jwtTokenKey";

	public static final String INVALID_TOKEN = "Invalid Jwt token";

}
