
package com.blog.controllers;

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
import org.springframework.web.bind.annotation.RestController;

import com.blog.dto.PostDto;
import com.blog.entity.Post;
import com.blog.exceptions.CategoryException;
import com.blog.exceptions.PostException;
import com.blog.exceptions.UserNotFoundException;
import com.blog.service.PostService;

@RestController 
@RequestMapping("/api/posts")
public class PostController {

	@Autowired
	private PostService pService;
	
	//Create Post
	    @PostMapping("/user/{userId}/category/{categoryId}")
		public ResponseEntity<PostDto> createPost(
				@RequestBody PostDto postDto,
				@PathVariable("userId") Integer userId,
				@PathVariable("categoryId") Integer categoryId) throws PostException, CategoryException,UserNotFoundException{
			
	    	PostDto newPost = pService.createPost(postDto, userId, categoryId);
			return new ResponseEntity<PostDto>(newPost,HttpStatus.CREATED);
		}
		//Update post
	    @PutMapping("/post/{postId}")
		public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable("postId") Integer postId) throws PostException{
			PostDto updatedPost = pService.updatePost(postDto, postId);
			return new ResponseEntity<PostDto>(updatedPost,HttpStatus.CREATED);
		}
		//Delete post
	    @DeleteMapping("/post/{postId}")
		public  ResponseEntity<String>  deletePost(@PathVariable("postId") Integer postId)throws PostException{
			String message = pService.deletePost(postId);
			return new ResponseEntity<String>(message,HttpStatus.OK);
		}
		//get all posts
	    @GetMapping("")
		public ResponseEntity<List<PostDto>> getAllPost()throws PostException{
			List<PostDto> listOfPostDto = pService.getAllPost();
			return new ResponseEntity<List<PostDto>>(listOfPostDto,HttpStatus.CREATED);
		}
		//get post by id
	    @GetMapping("/post/{postId}")
		public ResponseEntity<PostDto> getPostById(@PathVariable("postId") Integer postId)throws PostException{
			PostDto post = pService.getPostById(postId);
			return new ResponseEntity<PostDto>(post,HttpStatus.CREATED);
		}
		//get posts by category
	    @GetMapping("/category/{categoryId}")
		public  ResponseEntity<List<PostDto>>  getPostsByCategory(@PathVariable("categoryId") Integer categoryId)throws PostException,CategoryException{
			List<PostDto> listOfPostDtoByCategory = pService.getPostsByCategory(categoryId);
			return new ResponseEntity<List<PostDto>>(listOfPostDtoByCategory,HttpStatus.CREATED);
		}
		//get posts by user
		@GetMapping("/user/{userId}")
		public  ResponseEntity<List<PostDto>>  getPostsByUser(@PathVariable("userId") Integer userId)throws PostException,UserNotFoundException{
			List<PostDto> listOfPostDtoByUser = pService.getPostsByUser(userId);
			return new ResponseEntity<List<PostDto>>(listOfPostDtoByUser,HttpStatus.CREATED);
		}
		//get posts by search
		@GetMapping("/search/{keywords}")
		public ResponseEntity<List<PostDto>> searchPosts(@PathVariable("keywords") String keyword)throws PostException{
			List<PostDto> postByKeyword = pService.searchPosts(keyword);
			return new ResponseEntity<List<PostDto>>(postByKeyword,HttpStatus.CREATED);
		}
}
