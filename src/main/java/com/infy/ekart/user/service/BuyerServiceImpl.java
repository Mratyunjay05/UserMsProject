package com.infy.ekart.user.service;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.ekart.user.dto.BuyerDTO;
import com.infy.ekart.user.entity.Buyer;
import com.infy.ekart.user.exception.EkartException;
import com.infy.ekart.user.repository.BuyerRepository;
import com.infy.ekart.user.validator.BuyerValidator;

@Service(value = "buyerService")
@Transactional
public class BuyerServiceImpl implements BuyerService {
	
	@Autowired
	private BuyerRepository buyerRepository;
	
	@Override
	public BuyerDTO loginBuyer(String email, String password) throws EkartException {
		
		Buyer buyer =  buyerRepository.findByEmail(email);
		
		if(buyer == null) {
			throw new EkartException("Invalid Credentials");
		}
		
		String passwordFromDB = buyer.getPassword();
		if(passwordFromDB != null) {
			 
			try {
				if(password.equals(passwordFromDB)) {
					BuyerDTO buyerObject = new BuyerDTO();
					buyerObject.setBuyerId(buyer.getBuyerId());
					buyerObject.setName(buyer.getName());
					buyerObject.setEmail(buyer.getEmail());
					buyerObject.setPhoneNumber(buyer.getPhoneNumber());
					buyerObject.setPassword(buyer.getPassword());
					buyerObject.setIsPrivileged(buyer.getIsPrivileged());
					buyerObject.setRewardPoints(buyer.getRewardPoints());
					buyerObject.setIsActive(buyer.getIsActive());
					
					return buyerObject;
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
	public String registerBuyer(BuyerDTO buyerDTO) throws EkartException {
		
		BuyerValidator.validateBuyerForRegister(buyerDTO);
		if(buyerRepository.findByEmail(buyerDTO.getEmail()) !=null)
			throw new EkartException("Buyer Already Exists");
		
			
		Buyer buyerEntity = new Buyer();
		buyerEntity.setBuyerId(buyerDTO.getBuyerId());
		buyerEntity.setName(buyerDTO.getName());
		buyerEntity.setEmail(buyerDTO.getEmail());
		buyerEntity.setPhoneNumber(buyerDTO.getPhoneNumber());
		buyerEntity.setPassword(buyerDTO.getPassword());
		buyerEntity.setIsPrivileged(buyerDTO.getIsPrivileged());
		buyerEntity.setRewardPoints(buyerDTO.getRewardPoints());
		buyerEntity.setIsActive(buyerDTO.getIsActive());
			
		buyerRepository.save(buyerEntity);
			
		return buyerEntity.getName();
		
		
		
	}

	@Override
	public void deleteBuyer(String email) throws EkartException {
		Buyer buyer = buyerRepository.findByEmail(email);
		
		if(buyer == null) 
			throw new EkartException("Buyer Not Present");
		
		buyerRepository.delete(buyer);
	}
	
}
