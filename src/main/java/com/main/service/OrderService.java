package com.main.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.main.model.OrderBar;

@Service
public interface OrderService {

	void createOrder(OrderBar order);

	List<OrderBar> allOrders();

	OrderBar findByPrice(int price);

	Map<Integer,Float> showOrders(List<OrderBar> order, String oType);
	List<OrderBar> finByOrdertype(String oType);
	
	Map<Integer,Float> showordersbyMap(List<OrderBar> ord);

	void deleteOrder(int price);

}
