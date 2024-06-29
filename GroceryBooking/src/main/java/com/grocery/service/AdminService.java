package com.grocery.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.grocery.dto.ProductDto;
import com.grocery.dto.ResponseDto;

public interface AdminService {
	
	public List<ProductDto> loadAvailableProducts();
	public ResponseDto addItem(List<ProductDto> items);
	public ResponseDto deleteItem(String id);
	public ResponseDto updateItemDetails(List<ProductDto> items);
	public ResponseDto manageInventoryLevels(@RequestBody List<ProductDto> items);
}
