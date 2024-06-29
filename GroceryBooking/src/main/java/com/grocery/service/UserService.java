package com.grocery.service;

import java.util.List;

import com.grocery.dto.OrderDto;
import com.grocery.dto.ProductDto;
import com.grocery.dto.ResponseDto;

public interface UserService {

	public List<ProductDto> loadAvailableProducts();
	public ResponseDto placeOrder(List<OrderDto> orderList) ;
}
