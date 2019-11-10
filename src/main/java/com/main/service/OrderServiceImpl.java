package com.main.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.main.dao.OrderRepo;
import com.main.model.OrderBar;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepo oRepo;

	@Override
	public void createOrder(OrderBar order) {

		oRepo.save(order);
	}

	@Override
	public List<OrderBar> allOrders() {
		List<OrderBar> ls = (List<OrderBar>) oRepo.findAll();
		return ls;
	}

	@Override
	public OrderBar findByPrice(int price) {
		OrderBar order = oRepo.findByPriceInEuro(price);
		return order;
	}

	@Override
	public Map<Integer, Float> showOrders(List<OrderBar> order, String oType) {

		Set<Integer> stude = new HashSet<>();
		Set<Integer> studes = new HashSet<>();
		List<Integer> stu = new ArrayList<>();
		TreeMap<Integer, Float> map = new TreeMap<>();
		TreeMap<Integer, Float> map2 = new TreeMap<>(Collections.reverseOrder());
		

		String s = "BUY";

		for (OrderBar st : order) {
			System.out.println(st.getpriceInEuro());

			map.put(st.getpriceInEuro(), st.getorderQuantityInKg());
		}
		for (OrderBar se : order) {
			if (!stude.add(se.getpriceInEuro())) {
				studes.add(se.getpriceInEuro());
			}
		}

		for (Integer sd : studes) {
			stu.add(sd);
		}
		Collections.sort(stu);
		System.out.println("duplicate elements" + stu);
		float sum = 0;
		Collections.sort(order);
		for (int j = 0; j < stu.size(); j++) {
			for (int i = 0; i < order.size() - 1; i++) {
				if (stu.get(j) == order.get(i).getpriceInEuro()) {

					sum = sum + order.get(i).getorderQuantityInKg();

				}

			}
			System.out.println(sum);
			map.put(stu.get(j), sum);
			sum = 0;

		}
		
		if(oType.equalsIgnoreCase(s))
		{
			map2.putAll(map);
			
			return map2;
		}
		else
		{
			return map;
		}

		
	}

	@Override
	public void deleteOrder(int price) {
		oRepo.deleteByPriceInEuro(price);

	}

	@Override
	public List<OrderBar> finByOrdertype(String oType) {
		List<OrderBar> lis = oRepo.findByOrderType(oType);
		return lis;
	}

	@Override
	public Map<Integer, Float> showordersbyMap(List<OrderBar> ord) {

		String st = "SELL";
		Set<Integer> stude = new HashSet<>();
		List<Integer> stu = new ArrayList<>();
		List<Integer> buyStu = new ArrayList<>();
		Set<Integer> studes = new HashSet<>();
		Set<Integer> buyStude = new HashSet<>();
		Set<Integer> buyStudes = new HashSet<>();
		TreeMap<Integer, Float> hMap1 = new TreeMap<>();
		TreeMap<Integer, Float> hMap2 = new TreeMap<>(Collections.reverseOrder());
		for (OrderBar ob : ord) {
			if (ob.getOrderType().equalsIgnoreCase(st)) {
				hMap1.put(ob.getpriceInEuro(), ob.getorderQuantityInKg());
			} else {
				hMap2.put(ob.getpriceInEuro(), ob.getorderQuantityInKg());
			}
		}

		System.out.println("map1 values first inserted values");
		for (Map.Entry<Integer, Float> ma : hMap1.entrySet()) {

			System.out.println("key :" + ma.getKey() + " value is :" + ma.getValue());

		}
		System.out.println("map2 values first inserted values");
		for (Map.Entry<Integer, Float> ma : hMap2.entrySet()) {

			System.out.println("key :" + ma.getKey() + " value is :" + ma.getValue());

		}

		for (OrderBar se : ord) {
			if(se.getOrderType().equalsIgnoreCase(st))
			{
			if (!stude.add(se.getpriceInEuro())) {
				studes.add(se.getpriceInEuro());
			}
			}else
			{
				if (!buyStude.add(se.getpriceInEuro())) {
					buyStudes.add(se.getpriceInEuro());
				}
			}
		}
		for (Integer sd : studes) {
			stu.add(sd);
		}
		
		for(Integer buys:buyStudes)
		{
			buyStu.add(buys);
		}
		
		Collections.sort(stu);
		System.out.println("duplicate elements" + stu);
		float sum = 0, sum1 = 0;
		Collections.sort(ord);
		for (int j = 0; j < stu.size(); j++) {
			for (int i = 0; i < ord.size() - 1; i++) {
				if (ord.get(i).getOrderType().equalsIgnoreCase(st)) {
					if (stu.get(j) == ord.get(i).getpriceInEuro()) {

						sum =  (float) ((Math.round(sum + ord.get(i).getorderQuantityInKg()) * 10.0) / 10.0);
						
					}
				} 
			}
			System.out.println("sum vals" + sum);
			hMap1.put(stu.get(j), sum);
			
			sum = 0;
		}
		Collections.sort(buyStu);
		System.out.println("duplicate elements buystu" + buyStu);
		Collections.sort(ord);
		for (int j = 0; j < buyStu.size(); j++) {
			for (int i = 0; i < ord.size() - 1; i++) {
				if (ord.get(i).getOrderType().equalsIgnoreCase("BUY")) {
					if (buyStu.get(j) == ord.get(i).getpriceInEuro()) {

						sum1 = (float) ((Math.round(sum1 + ord.get(i).getorderQuantityInKg()) * 1000.0) / 1000.0);

					}
				} 

			}
			System.out.println("sum1 vals" + sum1);
			hMap2.put(buyStu.get(j), sum1);
			
			sum1 = 0;
		}
		
		
		
		

		System.out.println("map1 values");
		for (Map.Entry<Integer, Float> ma : hMap1.entrySet()) {

			System.out.println("key :" + ma.getKey() + " value is :" + ma.getValue());

		}
		System.out.println("map2 values");
		for (Map.Entry<Integer, Float> ma : hMap2.entrySet()) {

			System.out.println("key :" + ma.getKey() + " value is :" + ma.getValue());

		}
		LinkedHashMap<Integer,Float> m = new LinkedHashMap<>();
		m.putAll(hMap1);
		
		m.putAll(hMap2);
		
		
		for(Map.Entry<Integer, Float> mt:m.entrySet())
		{
			System.out.println("key:"+mt.getKey()+" value:"+mt.getValue());
		}
		
		return m;
	}

}
