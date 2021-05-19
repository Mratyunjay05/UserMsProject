package com.infy.ekart.user.service;

import com.infy.ekart.user.dto.CartDTO;

import com.infy.ekart.user.exception.EkartException;

public interface CartService {
	
	public String addProduct(CartDTO cartDTO) throws EkartException;
	
	public void deleteProduct(Integer prodId) throws EkartException;


}
