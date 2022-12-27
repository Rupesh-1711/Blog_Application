package com.blog.exceptions;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MyErrorDetail {

	private LocalDateTime timestamp;
	private String message;
	private String description;
}
