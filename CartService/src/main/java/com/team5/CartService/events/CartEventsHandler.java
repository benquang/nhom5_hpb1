package com.team5.CartService.events;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import com.team5.CartService.data.Cart;
import com.team5.CartService.data.CartRepository;
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

        //cart.setLineitems(null);
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
