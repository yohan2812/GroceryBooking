package com.grocery.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grocery.dto.OrderDto;
import com.grocery.dto.ProductDto;
import com.grocery.dto.ResponseDto;
import com.grocery.entity.Order;
import com.grocery.entity.Product;
import com.grocery.repository.OrderRepository;
import com.grocery.repository.ProductRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	ProductRepository prodRepo;

	@Autowired
	OrderRepository orderRepo;

	@Override
	public List<ProductDto> loadAvailableProducts() {
		List<Product> prodList=prodRepo.findAll(); 
		List<ProductDto> result = prodList.stream().filter(p->Objects.nonNull(p.getProductQty()) && p.getProductQty()>0)
				.map(p->ProductDto.builder().productId(p.getProductId()).productName(p.getProductName())
						.productPrice(p.getProductPrice()).productQty(p.getProductQty()).build())
				.collect(Collectors.toList());
		return result;
	}

	@Override
	public ResponseDto placeOrder(List<OrderDto> orderList) {
		orderList.forEach(orderItem->{
			Optional<Product> prodOptional = prodRepo.findById(orderItem.getProductId());
			if(prodOptional.isPresent()) {
				
				Product product = prodOptional.get();
				Double avlQty = product.getProductQty()-orderItem.getQuantity();

				product.setProductQty(avlQty);
				prodRepo.saveAndFlush(product);
				
				Order order=  Order.builder().productId(orderItem.getProductId()).quantity(orderItem.getQuantity())
						.unitPrice(orderItem.getUnitPrice()).userId(orderItem.getUserId())
						.build();
				orderRepo.save(order);
			}

		});
		return ResponseDto.builder().statusCode("200").message("Order Placed Successfully").build();
	}

}
