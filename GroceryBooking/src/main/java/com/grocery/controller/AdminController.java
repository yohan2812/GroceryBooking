package com.grocery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grocery.dto.ProductDto;
import com.grocery.dto.ResponseDto;
import com.grocery.service.AdminService;

@RestController
@RequestMapping("/api/v1")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@GetMapping("/products")
	public List<ProductDto> loadAvailableProducts() {
		return adminService.loadAvailableProducts();
	}
	@PostMapping("/add-items")
	public ResponseDto addItem(@RequestBody List<ProductDto> items) {
		return adminService.addItem(items);
	}
	
	@DeleteMapping("/{id}")
	public ResponseDto deleteItem(@PathVariable("id") String id) {
		return adminService.deleteItem(id);
	}
	
	@PutMapping("/modify-items-details")
	public ResponseDto updateItemDetails(@RequestBody List<ProductDto> items) {
		return adminService.updateItemDetails(items);
	}
	
	@PutMapping("/manage-inventory")
	public ResponseDto manageInventoryLevels(@RequestBody List<ProductDto> items) {
		return adminService.manageInventoryLevels(items);
	}
}
