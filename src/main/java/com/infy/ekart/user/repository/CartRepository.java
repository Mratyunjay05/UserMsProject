package com.infy.ekart.user.repository;

import org.springframework.data.repository.CrudRepository;

import com.infy.ekart.user.entity.Cart;

public interface CartRepository  extends CrudRepository<Cart, Integer>{

	public Cart findByProdId(Integer prodId);

	
}
