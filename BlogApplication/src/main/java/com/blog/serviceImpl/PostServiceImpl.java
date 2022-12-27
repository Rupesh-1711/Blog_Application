package com.blog.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dto.PostDto;
import com.blog.entity.Category;
import com.blog.entity.Post;
import com.blog.entity.User;
import com.blog.exceptions.CategoryException;
import com.blog.exceptions.PostException;
import com.blog.exceptions.UserNotFoundException;
import com.blog.repositories.CategoryRepo;
import com.blog.repositories.PostRepo;
import com.blog.repositories.UserRepo;
import com.blog.service.PostService;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostRepo pRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo uRepo;
	
	@Autowired
	private CategoryRepo cRepo;
	
	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) throws PostException, CategoryException,UserNotFoundException {
		
		User user = uRepo.findById(userId).orElseThrow(()->new UserNotFoundException("User Not found with this ID"));
		
		Category category = cRepo.findById(categoryId).orElseThrow(()->new CategoryException("Category not found with this id"));
		
		Post post  = modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post newPost = pRepo.save(post);
		return modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) throws PostException {
		Post post = pRepo.findById(postId).orElseThrow(()->new PostException("Post not found with provided Id"));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
       
        
        Post newPost = pRepo.save(post);
		
		return modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public String deletePost(Integer postId) throws PostException {
		    Post post= pRepo.findById(postId).orElseThrow(()->new PostException("Post Not found with provided Id"));
		    pRepo.delete(post);
		return "Post Deleted Successfully...";
	}

	@Override
	public List<PostDto> getAllPost() throws PostException {
		List<Post> allPosts = pRepo.findAll();
		List<PostDto> allPostDtos = allPosts.stream().map((post)-> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
	     if(allPostDtos.size()==0) {
	    	 throw new PostException("Posts Not Found !");
	     }
		return allPostDtos;
	}

	@Override
	public PostDto getPostById(Integer postId) throws PostException {
		Post post = pRepo.findById(postId).orElseThrow(()->new PostException("Post not found With Provided Id"));
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) throws PostException, CategoryException {
		Category cat = cRepo.findById(categoryId).orElseThrow(()->new CategoryException("Category Not found with provided categoryId"));
		
		List<Post> posts = pRepo.findByCategory(cat);
		
		List<PostDto> postDtos = posts.stream().map((post)->modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		if(postDtos.size()==0) {
			throw new PostException("No Post found with this category");
		}
		return postDtos;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) throws PostException, UserNotFoundException {
		User user = uRepo.findById(userId).orElseThrow(()-> new UserNotFoundException("User not found with this User Id"));
		List<Post> posts = pRepo.findByUser(user);
		List<PostDto> postDtos = posts.stream().map((post)->modelMapper.map(post,PostDto.class )).collect(Collectors.toList());
		if(postDtos.size()==0) {
			throw new PostException("Post not Found with Provided user");
		}
		return postDtos;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) throws PostException {
		// TODO Auto-generated method stub
		return null;
	}

}
