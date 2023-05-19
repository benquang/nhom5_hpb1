package com.team5.CartService.events;

import java.util.ArrayList;
import java.util.List;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import com.team5.CartService.data.Cart;
import com.team5.CartService.data.CartRepository;
import com.team5.CartService.data.LineItems;
import com.team5.CommonService.events.CartCancelledEvent;
import com.team5.CommonService.events.CartRemovedEvent;

@Component
public class CartEventsHandler {
	//private static Logger log = LoggerFactory.getLogger(OrderEventsHandler.class);

	private CartRepository cartRepository;
	
    public CartEventsHandler(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

	@EventHandler
	public void on(CartRemovedEvent event) {
        Cart cart = cartRepository.findByUser(event.getUserid());

        for (int i = cart.getLineitems().size() - 1; i >= 0; i--) {
        	cart.getLineitems().remove(i);
        }
        //List<LineItems> newlist = new ArrayList<>();
        
        //cart.setLineitems(newlist);
        
        cart.setCartstatus(event.getCardstatus());
        cart.setLastorder(event.getOrderid());

        cartRepository.save(cart);
		
	}
	
	@EventHandler
	public void on(CartCancelledEvent event) {
        Cart cart = cartRepository.findByLastorder(event.getOrderid());

        //cart.setLineitems(null);
        cart.setCartstatus(event.getCardstatus());

        cartRepository.save(cart);
		
	}
	
    
}
