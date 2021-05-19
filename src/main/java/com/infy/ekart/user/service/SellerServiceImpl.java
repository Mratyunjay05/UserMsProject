package com.infy.ekart.user.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.ekart.user.dto.SellerDTO;
import com.infy.ekart.user.entity.Seller;
import com.infy.ekart.user.exception.EkartException;
import com.infy.ekart.user.repository.SellerRepository;
import com.infy.ekart.user.validator.SellerValidator;
@Service(value = "sellerService")
@Transactional
public class SellerServiceImpl  implements SellerService{
	
	@Autowired
	SellerRepository sellerRepository;
	

	@Override
	public SellerDTO loginSeller(String email, String password) throws EkartException {
		
       Seller seller= sellerRepository.findByEmail(email);
		
		if(seller == null) {
			throw new EkartException("Invalid Credentials");
		}
		
		String passwordFromDB = seller.getPassword();
		if(passwordFromDB != null) {
			 
			try {
				if(password.equals(passwordFromDB)) {
					SellerDTO sellerObject = new SellerDTO();
					sellerObject.setSellerId(seller.getSellerId());
					sellerObject.setName(seller.getName());
					sellerObject.setEmail(seller.getEmail());
					sellerObject.setPhoneNumber(seller.getPhoneNumber());			
					sellerObject.setIsActive(seller.getIsActive());
					
					return sellerObject;
				}
				else
					throw new EkartException("Invalid Credentials");
			}
			catch(EkartException e) {
				throw new EkartException("Invalid Credentials");
			}
		}
		else
			throw new EkartException("Invalid Credentials");

	}
	
	
	
	@Override
	public String registerSeller(SellerDTO seller) throws EkartException {
		
		SellerValidator.validateSellerForRegister(seller);
		if(sellerRepository.findByEmail(seller.getEmail()) !=null)
			throw new EkartException("Buyer Already Exists");
		
			
		Seller sellerEntity = new Seller();
		sellerEntity.setSellerId(seller.getSellerId());
		sellerEntity.setName(seller.getName());
		sellerEntity.setEmail(seller.getEmail());
	    sellerEntity.setPhoneNumber(seller.getPhoneNumber());
		sellerEntity.setPassword(seller.getPassword());
		sellerEntity.setIsActive(seller.getIsActive());
			
		sellerRepository.save(sellerEntity);
			
		return sellerEntity.getName();
	}



	@Override
	public void deleteSeller(String email) throws EkartException {
		Seller seller = sellerRepository.findByEmail(email);
		if(seller == null) {
			throw new EkartException("Seller Not Present");
		}
		else {
			sellerRepository.delete(seller);
		}
		
	}
	
	

}
