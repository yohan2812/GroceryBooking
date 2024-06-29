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
public class ProductDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String productId;
	private String productName;
	private Double productQty;
	private Double productPrice; 
}
