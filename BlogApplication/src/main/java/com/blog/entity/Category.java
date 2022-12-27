package com.blog.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class Category {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	 private Integer categoryId;
	@Column(name="title")
	@NotBlank
	@Size(min = 3,message = "min 3 char required")
	 private String  categoryTitle;
	 private String   categoryDescription;
	 
	 @OneToMany(mappedBy ="category",cascade = CascadeType.ALL)
	 private List<Post> post = new ArrayList<>();

}
