package com.infy.ekart.user.validator;

import com.infy.ekart.user.dto.BuyerDTO;
import com.infy.ekart.user.exception.EkartException;

public class BuyerValidator {

	public static void validateBuyerForLogin(String email, String password) throws EkartException{
		
		if(!validateEmail(email))
			throw new EkartException("Enter Valid Email");
		if(!validatePassword(password))
			throw new EkartException("Enter Valid Password");
		
	}
	
	public static void validateBuyerForRegister(BuyerDTO buyer) throws EkartException{
		
		if(!validateEmail(buyer.getEmail()))
			throw new EkartException("Enter Valid Email");
		if(!validatePassword(buyer.getPassword()))
			throw new EkartException("Enter Valid Password");
		if(!validateUserName(buyer.getName()))
			throw new EkartException("Enter Valid Name");
		if(!validatePhoneNumber(buyer.getPhoneNumber()))
			throw new EkartException("Enter Valid Phone Number");
		
	}
	
	public static Boolean validateEmail(String email) {
		if(email==null)
			return false;
		return email.matches("[a-zA-Z._]{1,}@[a-zA-Z]{1,}.com$");
	}
	
	public static Boolean validatePassword(String password) {
		if(password==null)
			return false;
		Boolean flag = false;
		if(password.length() >= 7 && password.length() <= 20) 
			if(password.matches(".*[A-Z]+.*"))
				if(password.matches(".*[a-z]+.*"))
					if(password.matches(".*[0-9]+.*"))
						if(password.matches(".*[!@#$%^&*]+.*"))
							flag=true;
			
		return flag;	
		
	}
	
	public static Boolean validateUserName(String name) {
		if(name==null || name.trim().length() != name.length())
			return false;
		return name.matches("[A-Za-z ]+");
	}
	
	public static Boolean validatePhoneNumber(String phoneNumber) {
		if(phoneNumber==null)
			return false;
		Boolean flag = false;
		if(phoneNumber.matches("[6-9][0-9]{9}"))
			flag=true;
		return flag;
	}
}
