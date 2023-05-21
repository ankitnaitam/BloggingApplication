package com.bikkadit.blog.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bikkadit.blog.entities.Comment;
import com.bikkadit.blog.entities.Post;
import com.bikkadit.blog.exceptions.ResourceNotFoundException;
import com.bikkadit.blog.payloads.CommentDto;
import com.bikkadit.blog.repositories.CommentRepo;
import com.bikkadit.blog.repositories.PostRepo;
import com.bikkadit.blog.service.CommentService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private CommentRepo commentRepo;

	@Autowired
	private ModelMapper modelMapper;

	/**
	 * @author Ankit
	 * @implNote This implementation is to save comment
	 */
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		log.info("Initiated dao call for save the Comment");
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));

		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		Comment savedComment = this.commentRepo.save(comment);
		log.info("Completed dao call for save the Comment");
		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	/**
	 * @author Ankit
	 * @implNote This implementation is to delete comment
	 */
	@Override
	public void deleteComment(Integer comId) {
		log.info("Initiated dao call for delete the Comment");
		Comment comment = this.commentRepo.findById(comId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "Comment Id", comId));
		log.info("Completed dao call for delete the Comment");
		this.commentRepo.delete(comment);
	}

}
