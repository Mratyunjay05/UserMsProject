package com.infy.ekart.user.repository;

import org.springframework.data.repository.CrudRepository;

import com.infy.ekart.user.entity.Wishlist;

public interface WishlistRepository extends CrudRepository<Wishlist,Integer> {

	public Wishlist findByProdId(Integer prodId);
	
}
