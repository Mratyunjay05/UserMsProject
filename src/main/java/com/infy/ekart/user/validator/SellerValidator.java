package com.infy.ekart.user.validator;

import com.infy.ekart.user.dto.SellerDTO;
import com.infy.ekart.user.exception.EkartException;

public class SellerValidator {
	public static void validateSellerForLogin(String email,String password) throws EkartException{
		if(!validateEmail(email)){
			throw new EkartException("Enter Valid Email");
		}
		
		if(!validatePassword(password)) {
			throw  new EkartException("Enter Valid Password");	
		}
	}
	

	public static void validateSellerForRegister(SellerDTO seller) throws EkartException{
		if(!validateEmail(seller.getEmail())){
			throw new EkartException("Enter Valid Email");
		}
		
		if(!validatePassword(seller.getPassword())) {
			throw  new EkartException("Enter Valid Password");	
		}
		
		if(!validateContactNumber(seller.getPhoneNumber())) {
			throw new EkartException("Enter Valid Contact Number");
		}
		if(!validateSellerName(seller.getName())) {
			throw new EkartException("Enter Valid Name");
		}
	}


	private static boolean validateSellerName(String name) {
		if(name==null || name.trim().length() != name.length())
			return false;
		return name.matches("[A-Za-z ]+");
	}


	private static boolean validateContactNumber(String phoneNumber) {
		if(phoneNumber==null)
			return false;
		Boolean flag = false;
		if(phoneNumber.matches("[6-9][0-9]{9}"))
			flag=true;
		return flag;
	}


	private static boolean validatePassword(String password) {
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


	private static boolean validateEmail(String email) {
		if(email==null)
			return false;
		return email.matches("[a-zA-Z._]{1,}@[a-zA-Z]{1,}.com$");
	}

}
