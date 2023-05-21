package com.bikkadit.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bikkadit.blog.helper.ApiConstants;
import com.bikkadit.blog.helper.AppConstants;
import com.bikkadit.blog.payloads.ApiResponse;
import com.bikkadit.blog.payloads.CommentDto;
import com.bikkadit.blog.service.CommentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(value = ApiConstants.BASE_URL_COMMENT)
public class CommentController {

	@Autowired
	private CommentService commentService;

	/**
	 * @author Ankit
	 * @apiNote This Api is to save comment
	 * @param commentDto
	 * @param postId
	 * @return
	 */
	@PostMapping(value = ApiConstants.POST_ID)
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable Integer postId) {
		log.info("Initiated request for save the Comment");
		CommentDto comment = this.commentService.createComment(commentDto, postId);
		log.info("Completed request for save the Comment");
		return new ResponseEntity<CommentDto>(comment, HttpStatus.CREATED);
	}

	/**
	 * @author Ankit
	 * @apiNote This Api is to delete comment
	 * @param comId
	 * @return
	 */
	@DeleteMapping(value = ApiConstants.COM_ID)
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer comId) {
		log.info("Initiated request for delete the Comment");
		this.commentService.deleteComment(comId);
		log.info("Completed request for delete the Comment");
		return new ResponseEntity<ApiResponse>(new ApiResponse(AppConstants.COMMENT_DELETE + comId, true),
				HttpStatus.OK);
	}
}
