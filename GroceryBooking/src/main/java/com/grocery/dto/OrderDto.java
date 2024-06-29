package com.grocery.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
    private String userId;
    private String productId;
    private Double unitPrice;
    private Double quantity;
}
