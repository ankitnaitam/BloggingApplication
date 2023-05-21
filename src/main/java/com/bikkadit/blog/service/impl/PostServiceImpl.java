package com.bikkadit.blog.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bikkadit.blog.entities.Category;
import com.bikkadit.blog.entities.Post;
import com.bikkadit.blog.entities.User;
import com.bikkadit.blog.exceptions.ResourceNotFoundException;
import com.bikkadit.blog.payloads.PostDto;
import com.bikkadit.blog.payloads.PostResponse;
import com.bikkadit.blog.repositories.CategoryRepo;
import com.bikkadit.blog.repositories.PostRepo;
import com.bikkadit.blog.repositories.UserRepo;
import com.bikkadit.blog.service.PostService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ModelMapper modelMapper;

	/**
	 * @author Ankit
	 * @implNote This implementation is to save post data
	 */
	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		log.info("Initiated dao call for save the Post details with userId:{}, categoryId:{}", userId, categoryId);
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category ", "Category ID", categoryId));
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		Post newPost = this.postRepo.save(post);
		log.info("Completed dao call for save the Post details with userId:{}, categoryId:{}", userId, categoryId);
		return this.modelMapper.map(newPost, PostDto.class);
	}

	/**
	 * @author Ankit
	 * @implNote This implementation is to update post data
	 */
	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		log.info("Initiated dao call for update the Post details with postId:{}", postId);
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		Post updatedPost = this.postRepo.save(post);
		log.info("completed dao call for update the Post details with postId:{}", postId);
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	/**
	 * @author Ankit
	 * @implNote This implementation is to delete post data
	 */
	@Override
	public void deletePost(Integer postId) {
		log.info("Initiated dao call for delete the Post details with postId:{}", postId);
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));
		log.info("Completed dao call for delete the Post details with postId:{}", postId);
		this.postRepo.delete(post);
	}
	
	/**
	 * @author Ankit
	 * @implNote This implementation is to get single post data
	 */
	@Override
	public PostDto getPostById(Integer postId) {
		log.info("Initiated dao call to get the Post details with postId:{}", postId);
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));
		log.info("Completed dao call to get the Post details with postId:{}", postId);
		return this.modelMapper.map(post, PostDto.class);
	}

	/**
	 * @author Ankit
	 * @implNote This implementation is to get all post data
	 */
	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
		log.info("Initiated dao call to get all Post details");
//		Sort sort = null;
//		if (sortDir.equalsIgnoreCase("asc")) {
//			sort = Sort.by(sortBy).ascending();
//		} else {
//			sort = Sort.by(sortBy).descending();
//		}
//		
		// Using ternary operator
		Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

		PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> pagePost = this.postRepo.findAll(pageRequest);
		List<Post> posts = pagePost.getContent();
		List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		// object of PostResponse
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		log.info("Completed dao call to get all Post details");
		return postResponse;
	}

	/**
	 * @author Ankit
	 * @implNote This implementation is to get posts w.r.t category
	 */
	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		log.info("Initiated dao call to get the Post details with categoryId:{}", categoryId);
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));

		List<Post> posts = this.postRepo.findByCategory(category);

		List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return postDtos;
	}

	/**
	 * @author Ankit
	 * @implNote This implementation is to get posts w.r.t user
	 */
	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		log.info("Initiated dao call to get the Post details with userId:{}", userId);
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));
		List<Post> posts = this.postRepo.findByUser(user);
		List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		log.info("Completed dao call to get the Post details with userId:{}", userId);
		return postDtos;
	}

	/**
	 * @author Ankit
	 * @implNote This implementation is to search posts w.r.t title
	 */
	@Override
	public List<PostDto> searchPosts(String keywords) {
		log.info("Initiated dao call for search the Post details with Title keyword:{}", keywords);
		List<Post> posts = this.postRepo.findByTitleContaining(keywords);

//		List<Post> posts = this.postRepo.searchByTitle("%" + keywords + "%"); // for native query ("%") is mandatory

		List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		log.info("Completed dao call for search the Post details with Title keyword:{}", keywords);
		return postDtos;
	}

}
