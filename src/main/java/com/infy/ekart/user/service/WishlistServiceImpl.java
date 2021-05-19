package com.infy.ekart.user.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.infy.ekart.user.dto.WishlistDTO;
import com.infy.ekart.user.entity.Buyer;
import com.infy.ekart.user.entity.Wishlist;
import com.infy.ekart.user.exception.EkartException;
import com.infy.ekart.user.repository.BuyerRepository;
import com.infy.ekart.user.repository.WishlistRepository;

@Service(value = "wishlistService")
@Transactional
public class WishlistServiceImpl implements WishlistService{

	@Autowired
	private WishlistRepository wishlistRepository;
	@Autowired
	BuyerRepository br;
	@Override
	public String addProduct(WishlistDTO wishlist) throws EkartException {
		
		Wishlist wishlistEntity = new Wishlist();
		Buyer buyer = br.findByBuyerId(wishlist.getBuyerId());
		if(buyer!=null) {
			wishlistEntity.setBuyerId(wishlist.getBuyerId());
		}else {
			throw new EkartException("Buyer Not Present");
		}
		
		wishlistEntity.setProdId(wishlist.getProdId());
		
		wishlistRepository.save(wishlistEntity);
		
		return "your product";
	}
	
	@Override
	public void deleteProduct(Integer prodId) throws EkartException {
		
		Wishlist wishlist = wishlistRepository.findByProdId(prodId);
		
		if(wishlist==null) {
			throw new EkartException("product not present");
		}
		
		wishlistRepository.delete(wishlist);
	}

}
