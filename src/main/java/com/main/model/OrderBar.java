package com.main.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Component
@Entity
public class OrderBar implements Comparable<OrderBar>{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	private float orderQuantityInKg;
	private int priceInEuro;
	private String orderType;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public float getorderQuantityInKg() {
		return orderQuantityInKg;
	}

	public void setorderQuantityInKg(float orderQuantityInKg) {
		this.orderQuantityInKg = orderQuantityInKg;
	}

	public int getpriceInEuro() {
		return priceInEuro;
	}

	public void setpriceInEuro(int priceInEuro) {
		this.priceInEuro = priceInEuro;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public OrderBar(int userId, float orderQuantityInKg, int priceInEuro, String orderType) {
		super();
		this.userId = userId;
		this.orderQuantityInKg = orderQuantityInKg;
		this.priceInEuro = priceInEuro;
		this.orderType = orderType;

	}

	public OrderBar() {
		super();
	}

	@Override
	public int compareTo(OrderBar o) {
		
		if(this.getpriceInEuro()<o.getpriceInEuro())
		{
			return -1;
		}else
		{
			return 1;
		}
		
	}

	
}
