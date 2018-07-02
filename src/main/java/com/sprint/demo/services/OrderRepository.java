package com.sprint.demo.services;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.sprint.demo.models.OrderDetails;

@Configuration
public interface OrderRepository extends MongoRepository<OrderDetails, String> {

//public int insertIntoDB(OrderDetails orderDetails);
public OrderDetails findByOrderId(String orderId);
public void deleteByOrderId(String orderId);
	
}