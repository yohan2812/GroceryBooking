package com.grocery.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private String statusCode;
	private String message;
	
	
}
