package com.grocery.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grocery.dto.ProductDto;
import com.grocery.dto.ResponseDto;
import com.grocery.entity.Product;
import com.grocery.repository.ProductRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	ProductRepository prodRepo;
	
	@Override
	public List<ProductDto> loadAvailableProducts() {
		List<Product> prodList=prodRepo.findAll(); 
		List<ProductDto> result = prodList.stream()
		.map(p->ProductDto.builder().productId(p.getProductId()).productName(p.getProductName())
				.productPrice(p.getProductPrice()).productQty(p.getProductQty()).build())
		.collect(Collectors.toList());
		return result;
	}
	
	@Override
	public ResponseDto addItem(List<ProductDto> items) {
		items.forEach(item->{
			Optional<Product> prodOptional = prodRepo.findById(item.getProductId());
			if(!prodOptional.isPresent()) {
				Product product = Product.builder().productId(item.getProductId())
						.productName(item.getProductName())
						.productPrice(item.getProductPrice())
						.productQty(item.getProductQty()).build();
				prodRepo.save(product);
			}
		});
		return ResponseDto.builder().statusCode("200").message("Items Saved Successfully").build();
	}

	@Override
	public ResponseDto deleteItem(String id) {
		Optional<Product> prodOptional = prodRepo.findById(id);
		if(prodOptional.isPresent()) {
			String itemName = prodOptional.get().getProductName();
			prodRepo.deleteById(id);
			return ResponseDto.builder().statusCode("200").message(itemName+ " deleted from inventory").build();
		}
		return ResponseDto.builder().statusCode("200").message("No item found").build();
	}

	@Override
	public ResponseDto updateItemDetails(List<ProductDto> items) {
		items.forEach(item->{
			Optional<Product> prodOptional = prodRepo.findById(item.getProductId());
			if(prodOptional.isPresent()) {
				Product product = prodOptional.get();
				product.setProductName(item.getProductName());
				product.setProductPrice(item.getProductPrice());	
				prodRepo.save(product);
			}
		});
		return ResponseDto.builder().statusCode("200").message("Items Updated Successfully").build();
	}

	@Override
	public ResponseDto manageInventoryLevels(List<ProductDto> items) {
		items.forEach(item->{
			Optional<Product> prodOptional = prodRepo.findById(item.getProductId());
			if(prodOptional.isPresent()) {
				Product product = prodOptional.get();
				product.setProductQty(item.getProductQty());
				prodRepo.save(product);
			}
		});
		return ResponseDto.builder().statusCode("200").message("Inventory Managed Successfully").build();
	}

}
