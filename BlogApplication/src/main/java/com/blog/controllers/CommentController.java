package com.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.exceptions.CommentException;
import com.blog.serviceImpl.CommentService;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

	@Autowired
	private CommentService cService;
	
	@PostMapping("/post/{postId}")
	public ResponseEntity<CommentDto>  createComment(@RequestBody CommentDto commentDto,@PathVariable("postId") Integer postId)throws CommentException{
		CommentDto createdcom = cService.createComment(commentDto, postId);
		
		return new ResponseEntity<CommentDto>(createdcom,HttpStatus.CREATED);
	}

	@DeleteMapping("/comment/{commentId}")
	public ResponseEntity<String> deleteComment(@PathVariable("commentId") Integer commentId)throws CommentException{
		String message = cService.deleteComment(commentId);
		return new ResponseEntity<String>(message,HttpStatus.OK);
	}
}
