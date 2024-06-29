package com.grocery.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Order {

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "order_id")
    private Integer orderId;
	
	@Column(name = "user_id")
    private String userId;
	
	@Column(name = "product_id")
    private String productId;
	
	@Column(name = "unit_price")
    private Double unitPrice;
	
	@Column(name = "quantity")
    private Double quantity;
}
