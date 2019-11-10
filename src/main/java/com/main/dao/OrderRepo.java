package com.main.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.main.model.OrderBar;

public interface OrderRepo extends CrudRepository<OrderBar, Integer> {

	
	  OrderBar findByPriceInEuro(float price);
	  
	  void deleteByPriceInEuro(int price);

	List<OrderBar> findByOrderType(String oType);
	 

}
