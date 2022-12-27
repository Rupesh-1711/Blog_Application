package com.blog.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CategoryDto {

	
	 private Integer categoryId;
	  @NotBlank
	  @Size(min = 3,message = "min 3 char required")
	 private String  categoryTitle;
	 private String   categoryDescription;
}
