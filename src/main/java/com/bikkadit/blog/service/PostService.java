package com.bikkadit.blog.service;

import java.util.List;

import com.bikkadit.blog.payloads.PostDto;
import com.bikkadit.blog.payloads.PostResponse;

public interface PostService {

	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

	PostDto updatePost(PostDto postDto, Integer postId);

	void deletePost(Integer postId);

	PostDto getPostById(Integer postId);

	// List<PostDto>
	PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

	List<PostDto> getPostsByCategory(Integer categoryId);

	List<PostDto> getPostsByUser(Integer userId);

	List<PostDto> searchPosts(String keywords);

}
