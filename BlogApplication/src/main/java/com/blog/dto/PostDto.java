package com.blog.dto;



import java.util.Date;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import com.blog.entity.Category;
import com.blog.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PostDto {


	private Integer postId;
	@NotBlank
	private String title;
	@NotBlank
	private String content;
	
	private String imageName;
	
	private Date addedDate;
	
//	@JsonIgnore
	private CategoryDto category;
//	@JsonIgnore
	private UserDto user;
	
}
