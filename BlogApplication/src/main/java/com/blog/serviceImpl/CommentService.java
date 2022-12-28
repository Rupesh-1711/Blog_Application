package com.blog.serviceImpl;

import com.blog.controllers.CommentDto;
import com.blog.exceptions.CommentException;

public interface CommentService {

	public CommentDto createComment(CommentDto commentDto,Integer postId)throws CommentException;

	public String deleteComment(Integer commentId)throws CommentException;
}
