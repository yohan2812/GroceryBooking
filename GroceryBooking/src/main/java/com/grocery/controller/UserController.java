package com.grocery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grocery.dto.OrderDto;
import com.grocery.dto.ProductDto;
import com.grocery.dto.ResponseDto;
import com.grocery.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/health")
	public String health() {
		return "Application Status : Up";
	}
	
	@GetMapping("/products")
	public List<ProductDto> loadAvailableProducts() {
		return userService.loadAvailableProducts();
	}
	
	@PostMapping("/order")
	public ResponseDto placeOrder(@RequestBody List<OrderDto> orderList) {
		return userService.placeOrder(orderList);
	}
}
