package com.infy.ekart.user.service;

import com.infy.ekart.user.dto.SellerDTO;
import com.infy.ekart.user.exception.EkartException;

public interface SellerService {
	public SellerDTO loginSeller(String email, String password) throws EkartException;
	
	public String registerSeller(SellerDTO buyer) throws EkartException; 
	
	public void deleteSeller(String seller) throws EkartException;
}
