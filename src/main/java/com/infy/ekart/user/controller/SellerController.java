package com.infy.ekart.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infy.ekart.user.dto.ProductDTO;
import com.infy.ekart.user.dto.SellerDTO;
import com.infy.ekart.user.exception.EkartException;
import com.infy.ekart.user.service.SellerService;

@RestController
@RequestMapping(value = "/Seller")
public class SellerController {

	@Autowired
	private SellerService sellerService;
	

	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public ResponseEntity<SellerDTO> SellerLogin(@RequestBody SellerDTO seller) throws EkartException{
			
		SellerDTO sellerServ =  sellerService.loginSeller(seller.getEmail(), seller.getPassword());
		return new ResponseEntity<>(sellerServ, HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/sellerRegister", method = RequestMethod.POST)
	public ResponseEntity<String> sellerRegister(@RequestBody SellerDTO seller) throws EkartException{
			
		String sellerServ =  sellerService.registerSeller(seller);
		
		String mess = sellerServ + " successfully registered";
		
		
		
		return new ResponseEntity<>(mess, HttpStatus.OK);
		
	}
	
	@DeleteMapping(value="/sellerDelete/{email}")
	public ResponseEntity<String> sellerDelete(@PathVariable String email) throws EkartException{
		
		sellerService.deleteSeller(email);
		
		String sm = "Seller successfully deleted";
		
		return new ResponseEntity<>(sm, HttpStatus.OK); 
	}
	
	@RequestMapping(value="/sellerAddProduct", method = RequestMethod.POST)
	public ResponseEntity<String> productAddedBySeller(@RequestBody ProductDTO product) throws EkartException, JsonProcessingException
	{
		RestTemplate restTemplate = new RestTemplate();

        ObjectMapper Obj = new ObjectMapper();

        String jsonStr = Obj.writeValueAsString(product);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(jsonStr,headers);

		String sm = restTemplate.postForObject("http://localhost:8100/Product/productadd", entity, String.class);
		
		return new ResponseEntity<>(sm+" added successfuly", HttpStatus.OK);

	}
	
	@RequestMapping(value="/sellerDeleteProduct/{productName}",method = RequestMethod.DELETE)
	public ResponseEntity<String> productDeletedBySeller(@PathVariable String productName) throws EkartException {
		
		RestTemplate restTemplate = new RestTemplate();
		String message = "";

		ProductDTO productDTO = restTemplate.getForObject("http://localhost:8100/Product/product/"+productName,ProductDTO.class);
		
		if(productDTO!=null) {
			restTemplate.delete("http://localhost:8100/Product//productdelete/"+productName);
			message = "Product deleted successfully";
			
		}
		else {
			throw new EkartException("product does not exists");
		}
		
		
		return new ResponseEntity<>(message, HttpStatus.OK);

		
	}

	
}
