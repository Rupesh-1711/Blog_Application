package com.blog.service;

import java.util.List;

import com.blog.dto.PostDto;
import com.blog.dto.PostResponse;
import com.blog.entity.Category;
import com.blog.entity.Post;
import com.blog.entity.User;
import com.blog.exceptions.CategoryException;
import com.blog.exceptions.PostException;
import com.blog.exceptions.UserNotFoundException;

public interface PostService {

	//Create Post
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) throws PostException, CategoryException,UserNotFoundException;
	//Update post
	public PostDto updatePost(PostDto postDto,Integer postId) throws PostException;
	//Delete post
	public String deletePost(Integer postId)throws PostException;
	//get all posts
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir)throws PostException;
	//get post by id
	public PostDto getPostById(Integer postId)throws PostException;
	//get posts by category
	public List<PostDto> getPostsByCategory(Integer categoryId)throws PostException,CategoryException;
	//get posts by user
	public List<PostDto> getPostsByUser(Integer userId)throws PostException,UserNotFoundException;
	//get posts by search
	public List<PostDto> searchPosts(String keyword)throws PostException;
	
}
