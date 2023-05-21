package com.bikkadit.blog.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bikkadit.blog.helper.ApiConstants;
import com.bikkadit.blog.helper.AppConstants;
import com.bikkadit.blog.payloads.ApiResponse;
import com.bikkadit.blog.payloads.PostDto;
import com.bikkadit.blog.payloads.PostResponse;
import com.bikkadit.blog.service.PostService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(value = ApiConstants.BASE_URL_POST)
public class PostController {

	@Autowired
	private PostService postService;

	/**
	 * @author Ankit
	 * @apiNote This Api is to save post data
	 * @param postDto
	 * @param userId
	 * @param categoryId
	 * @return
	 */
	@PostMapping(value = ApiConstants.POST_CREATE)
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {
		log.info("Initiated request for save the Post details with userId:{}, categoryId:{}", userId, categoryId);
		PostDto createPostDto = this.postService.createPost(postDto, userId, categoryId);
		log.info("Completed request for save the Post details with userId:{}, categoryId:{}", userId, categoryId);
		return new ResponseEntity<PostDto>(createPostDto, HttpStatus.CREATED);
	}

	/**
	 * @author Ankit
	 * @apiNote This Api is to get single post data
	 * @param postId
	 * @return
	 */
	@GetMapping(value = ApiConstants.POST_ID)
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
		log.info("Initiated request to get the Post details with postId:{}", postId);
		PostDto postDto = this.postService.getPostById(postId);
		log.info("Completed request to get the Post details with postId:{}", postId);
		return new ResponseEntity<PostDto>(postDto, HttpStatus.FOUND);
	}

	/**
	 * @author Ankit
	 * @apiNote This Api is to get all posts data
	 * @param pageNumber
	 * @param pageSize
	 * @param sortBy
	 * @param sortDir
	 * @return
	 */
	@GetMapping()
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir) {
		log.info("Initiated request to get all Post details");
		PostResponse postResponse = this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);
		log.info("Completed request to get all Post details");
		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.FOUND);
	}

	/**
	 * @author Ankit
	 * @apiNote This Api is to get posts data w.r.t user
	 * @param userId
	 * @return
	 */
	@GetMapping(value = ApiConstants.POSTS_GET_BY_USER)
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId) {
		log.info("Initiated request to get the Post details with userId:{}", userId);
		List<PostDto> postDtos = this.postService.getPostsByUser(userId);
		log.info("Completed request to get the Post details with userId:{}", userId);
		return new ResponseEntity<List<PostDto>>(postDtos, HttpStatus.FOUND);
	}

	/**
	 * @author Ankit
	 * @apiNote This Api is to get posts data w.r.t category
	 * @param categoryId
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@GetMapping(value = ApiConstants.POSTS_GET_BY_CATEGORY)
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId) {
		log.info("Initiated request to get the Post details with categoryId:{}", categoryId);
		List<PostDto> posts = this.postService.getPostsByCategory(categoryId);
		log.info("Completed request to get the Post details with categoryId:{}", categoryId);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.FOUND);
	}

	/**
	 * @author Ankit
	 * @apiNote This Api is to update post data
	 * @param postDto
	 * @param postId
	 * @return
	 */
	@PutMapping(value = ApiConstants.POST_ID)
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {
		log.info("Initiated request for update the Post details with postId:{}", postId);
		PostDto updatedPostDto = this.postService.updatePost(postDto, postId);
		log.info("Completed request for update the Post details with postId:{}", postId);
		return new ResponseEntity<PostDto>(updatedPostDto, HttpStatus.OK);
	}

	/**
	 * @author Ankit
	 * @apiNote This Api is to delete post data
	 * @param postId
	 * @return
	 */
	@DeleteMapping(value = ApiConstants.POST_ID)
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId) {
		log.info("Initiated request for delete the Post details with postId:{}", postId);
		this.postService.deletePost(postId);
		log.info("Completed request for delete the Post details with postId:{}", postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse(AppConstants.POST_DELETE + postId, true), HttpStatus.OK);
	}

	/**
	 * @author Ankit
	 * @apiNote This Api is to search post data w.r.t title
	 * @param keywords
	 * @return
	 */
	@GetMapping(value = ApiConstants.POSTS_SEARCH)
	public ResponseEntity<List<PostDto>> searchPostsByTitle(@PathVariable("keywords") String keywords) {
		log.info("Initiated request for search the Post details with Title keyword:{}", keywords);
		List<PostDto> result = this.postService.searchPosts(keywords);
		log.info("Completed request for search the Post details with Title keyword:{}", keywords);
		return new ResponseEntity<List<PostDto>>(result, HttpStatus.OK);
	}
}
