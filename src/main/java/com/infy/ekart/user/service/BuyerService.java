package com.infy.ekart.user.service;

import com.infy.ekart.user.dto.BuyerDTO;
import com.infy.ekart.user.exception.EkartException;

public interface BuyerService {
	public BuyerDTO loginBuyer(String email, String password) throws EkartException;
	
	public String registerBuyer(BuyerDTO buyer) throws EkartException; 
	
	public void deleteBuyer(String email) throws EkartException;
}
