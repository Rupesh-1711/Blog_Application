package com.blog.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.controllers.CommentDto;
import com.blog.entity.Comment;
import com.blog.entity.Post;
import com.blog.exceptions.CommentException;
import com.blog.repositories.CommentRepo;
import com.blog.repositories.PostRepo;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private PostRepo pRepo;
	
	@Autowired
	private CommentRepo cRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) throws CommentException {
	
		Post post = pRepo.findById(postId).orElseThrow(()->new CommentException("Post not Found......"));
		
		Comment comment = modelMapper.map(commentDto, Comment.class);
		
		comment.setPost(post);
		
		Comment savedComment = cRepo.save(comment);
		
		return modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public String deleteComment(Integer commentId) throws CommentException {
		Comment comment = cRepo.findById(commentId).orElseThrow(()->new CommentException("Comment not found with this Id"));
		cRepo.delete(comment);
		return "Comment deleted Successfully....";
	}

}
