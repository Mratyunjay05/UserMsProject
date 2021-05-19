package com.infy.ekart.user.repository;

import org.springframework.data.repository.CrudRepository;

import com.infy.ekart.user.entity.Seller;

public interface SellerRepository extends CrudRepository<Seller,Integer>{
	public Seller findByEmail(String email);
	
	
}
