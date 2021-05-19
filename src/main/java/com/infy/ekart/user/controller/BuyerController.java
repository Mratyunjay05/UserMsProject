package com.infy.ekart.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.infy.ekart.user.dto.BuyerDTO;
import com.infy.ekart.user.exception.EkartException;
import com.infy.ekart.user.service.BuyerService;

@RestController
@RequestMapping(value = "/Buyer")
public class BuyerController {
	
	@Autowired
	private BuyerService buyerService;
	
	
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public ResponseEntity<BuyerDTO> buyerLogin(@RequestBody BuyerDTO buyer) throws EkartException{
			
		BuyerDTO buyerServ =  buyerService.loginBuyer(buyer.getEmail(), buyer.getPassword());
		return new ResponseEntity<BuyerDTO>(buyerServ, HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/buyerRegister", method = RequestMethod.POST)
	public ResponseEntity<String> buyerRegister(@RequestBody BuyerDTO buyer) throws EkartException{
			
		String buyerServ =  buyerService.registerBuyer(buyer);
		
		String sm = buyerServ + " successfully registered";
		
		
		
		return new ResponseEntity<>(sm, HttpStatus.OK);
		
	}
	
	@DeleteMapping(value="/buyerDelete/{email}")
	public ResponseEntity<String> buyerDelete(@PathVariable String email) throws EkartException{
		
		buyerService.deleteBuyer(email);
		
		String sm = "buyer successfully deleted";
		
		return new ResponseEntity<>(sm, HttpStatus.OK); 
	}
	
	
}
