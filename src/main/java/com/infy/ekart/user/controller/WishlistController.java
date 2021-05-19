package com.infy.ekart.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.infy.ekart.user.dto.ProductDTO;
import com.infy.ekart.user.dto.WishlistDTO;
import com.infy.ekart.user.exception.EkartException;
import com.infy.ekart.user.service.WishlistService;

@RestController
@RequestMapping(value = "/Wishlist")
public class WishlistController {

	@Autowired
	private WishlistService wishlistService;
	
	@Value("${product.uri}")
	String productUri;
	
	@PostMapping(value = "/add")
	public ResponseEntity<String> addProductToWishlist(@RequestBody WishlistDTO wishlist) throws EkartException{
		
		String buyerServ =  wishlistService.addProduct(wishlist);
		String sm = "";
		//String wishlistString = wishlistService.addProduct(wishlist);
		
		ProductDTO productDTO = new RestTemplate().getForObject("http://localhost:8100/Product/productid/"+wishlist.getProdId(), ProductDTO.class);

		if(productDTO!=null) {
			sm = buyerServ + " successfully added";
		}
		else {
			throw new EkartException("product does not exists");
		}
		
		
		return new ResponseEntity<>(sm, HttpStatus.OK);
		
	}

	@DeleteMapping(value = "/delete/{prodId}")
	public ResponseEntity<String> deleteProductFromWishlist(@PathVariable Integer prodId) throws EkartException{
		
		wishlistService.deleteProduct(prodId);
		
		String sm = "product successfully deleted from";
		
		return new ResponseEntity<>(sm, HttpStatus.OK); 
	
	}

	
}
