package com.infy.ekart.user.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.ekart.user.dto.CartDTO;
import com.infy.ekart.user.entity.Buyer;
import com.infy.ekart.user.entity.Cart;
import com.infy.ekart.user.exception.EkartException;
import com.infy.ekart.user.repository.BuyerRepository;
import com.infy.ekart.user.repository.CartRepository;
@Service(value = "cartService")
@Transactional
public class CartServiceImpl implements CartService {
	@Autowired
	CartRepository cartRepository;
	@Autowired
     BuyerRepository br;
	@Override
	public String addProduct(CartDTO cartDTO) throws EkartException {

		Cart cartEntity = new Cart();
		Buyer buyer = br.findByBuyerId(cartDTO.getBuyerId());
		if(buyer!=null) {
			cartEntity.setBuyerId(cartDTO.getBuyerId());
		}else {
			throw new EkartException("Buyer Not Present");
		}
		cartEntity.setBuyerId(cartDTO.getBuyerId());
		cartEntity.setProdId(cartDTO.getProdId());
		cartEntity.setQuantity(cartDTO.getQuantity());
		
		cartRepository.save(cartEntity);
		
		return "your Prouduct ";
	
	}
	
	@Override
	public void deleteProduct(Integer prodId) throws EkartException {
		
		Cart cart = cartRepository.findByProdId(prodId);
		
		if(cart==null) {
			throw new EkartException("product not present in cart");
		}
		
		cartRepository.delete(cart);
	}

}
