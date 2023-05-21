package com.bikkadit.blog.service;

import com.bikkadit.blog.payloads.CommentDto;

public interface CommentService {

	CommentDto createComment(CommentDto commentDto, Integer postId);

	void deleteComment(Integer comId);

}
