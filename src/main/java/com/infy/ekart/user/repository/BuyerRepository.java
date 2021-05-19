package com.infy.ekart.user.repository;


import org.springframework.data.repository.CrudRepository;


import com.infy.ekart.user.entity.Buyer;

public interface BuyerRepository extends CrudRepository<Buyer , Integer> {
	
	public Buyer findByEmail(String email);

	public Buyer findByBuyerId(Integer buyerId);
	
	//public Buyer deleteByEmail(String email);
}
