package com.team5.CartService.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team5.CartService.data.Cart;
import com.team5.CartService.data.CartRepository;
import com.team5.CartService.data.LineItems;
import com.team5.CartService.model.CartRestModel;

@RestController
@RequestMapping("/carts")
@CrossOrigin("http://localhost:3000")
public class CartController {
	
	private static Logger log = LoggerFactory.getLogger(CartController.class);
	
	private CartRepository cartrepository;
	
	public CartController(CartRepository cartrepository) {
		this.cartrepository = cartrepository;
	}
    
	//gio hang
	@GetMapping("/{user}")
	public Cart getCart(@PathVariable("user") String user) {
		
        //log.info("User Id: {}",
        //		cartrestmodel.getUser());
        
		Cart cart = cartrepository.findByUser(user);
		
		return cart;
		
	}
	
	//quantity san pham trong gio hang
	@PostMapping("/quantity")
	public int getProductQuantity(@RequestBody CartRestModel cartrestmodel) {
		
        //log.info("User Id: {}",
        //		cartrestmodel.getUser());
        
		Cart cart = cartrepository.findByUser(cartrestmodel.getUser());
		
		int quantity = 0;
		
		for (int i = 0; i < cart.getLineitems().size(); i++) {
			if (cart.getLineitems().get(i).getProduct().equals(cartrestmodel.getProduct())) {
				
				quantity = cart.getLineitems().get(i).getQuantity();
				break;
			}
		}
		
		return quantity;
	}
	
	//add sanpham, update quantity san pham
	@PostMapping
	public int upProductQuantity(@RequestBody CartRestModel cartrestmodel) {
   
		Cart cart = cartrepository.findByUser(cartrestmodel.getUser());
		
		int quantity = cartrestmodel.getQuantity();
		
		boolean is_product_new = true;
		for (int i = 0; i < cart.getLineitems().size(); i++) {
			if (cart.getLineitems().get(i).getProduct().equals(cartrestmodel.getProduct())) {
				
				cart.getLineitems().get(i).setQuantity(quantity);
				
				//huy san pham trong cart
				if(quantity == 0) {
					cart.getLineitems().remove(i);
				}
				
				cartrepository.save(cart);
				
				is_product_new = false;
			}
			
		}
		
		//neu ko co product, them moi
		
		if(is_product_new == true) {
			LineItems lineitem = new LineItems();
			lineitem.setProduct(cartrestmodel.getProduct());
			lineitem.setQuantity(cartrestmodel.getQuantity());
			//lineitem.setUnitprice(cartrestmodel.getUnitprice());
			
			
			cart.getLineitems().add(lineitem);
			
			cartrepository.save(cart);
		}
		
		
		return quantity;
	}
}
