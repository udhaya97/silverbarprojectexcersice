package com.main.controller;

import java.util.*;

//import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.model.OrderBar;
import com.main.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	OrderService oServ;
	// public static final Logger logger =
	// LoggerFactory.getLogger(OrderController.class);

	@PostMapping("/register")
	public ResponseEntity<?> saveOrder(@RequestBody(required = false) OrderBar order) {
		try {

			// logger.info("Hai Udhaya");
			if (order == null) {
				return new ResponseEntity<>("enter the details in request body", HttpStatus.BAD_REQUEST);
			} else {
				float salary = (float) (Math.round((order.getorderQuantityInKg()) * 10.0) / 10.0);
				System.out.println("decimal point" + salary);
				order.setorderQuantityInKg(salary);

				// order.setorderQuantityInKg(df.format(order.getorderQuantityInKg()));
				oServ.createOrder(order);
			}
		} catch (NullPointerException ne) {
			// return new ResponseEntity<>(order);
			return new ResponseEntity<>("enter the values", HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>("Registered Successfully", HttpStatus.OK);
	}

	@GetMapping(value = "/allorder/{orderType}")
	public ResponseEntity<Map<Integer, Float>> getAllOrder(@PathVariable("orderType") String ordType) {
		Map<Integer, Float> map = new TreeMap<>();
		try {

			List<OrderBar> lOrder = oServ.finByOrdertype(ordType);

			map = oServ.showOrders(lOrder, ordType);
		} catch (NullPointerException nee) {

			throw new NullPointerException("Please do register order...");
		}

		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@GetMapping("/liveorders")
	public ResponseEntity<Map<Integer, Float>> getliveorders() {
		Map<Integer, Float> hMap = new LinkedHashMap<>();
		try {
			// logger.info("Values getting from live order");
			List<OrderBar> orBar = oServ.allOrders();

			hMap = oServ.showordersbyMap(orBar);

		} catch (NullPointerException ne) {
			throw new NullPointerException("Data not available,please register order");
		}

		return new ResponseEntity<>(hMap, HttpStatus.OK);

	}

	@DeleteMapping("/cancel/{price}")
	public ResponseEntity<?> deleteOrder(@PathVariable int price) {
		oServ.deleteOrder(price);
		return new ResponseEntity<>("order cancelled successfully", HttpStatus.OK);
	}

}
