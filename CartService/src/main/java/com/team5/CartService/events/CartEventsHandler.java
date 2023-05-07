package com.team5.CartService.events;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import com.team5.CartService.data.Cart;
import com.team5.CartService.data.CartRepository;
import com.team5.CommonService.events.EditCartEvent;

@Component
public class CartEventsHandler {
	//private static Logger log = LoggerFactory.getLogger(OrderEventsHandler.class);

	private CartRepository cartRepository;
	
    public CartEventsHandler(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

	@EventHandler
	public void on(EditCartEvent event) {
        Cart cart = cartRepository.findByUser(event.getUser());

        cart.setLineitems(null);
        cart.setCartstatus(event.getCardstatus());

        cartRepository.save(cart);
		
	}
	
    
}
