package com.team5.CartService.aggregate;

import java.util.List;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.team5.CommonService.command.OrderCancelCommand;
import com.team5.CommonService.command.OrderCompleteCommand;
import com.team5.CommonService.command.PaymentCancelCommand;
import com.team5.CommonService.command.CartCancelCommand;
import com.team5.CommonService.command.CartRemoveCommand;
import com.team5.CommonService.events.CartCancelledEvent;
import com.team5.CommonService.events.CartRemovedEvent;
import com.team5.CommonService.events.OrderCancelledEvent;
import com.team5.CommonService.events.OrderCompletedEvent;
import com.team5.CommonService.events.PaymentCanceledEvent;

@Aggregate
public class CartAggregate {
	private static Logger log = LoggerFactory.getLogger(CartAggregate.class);
	
	@AggregateIdentifier
	private String cart;
	private String orderid;
	private String cartstatus;
	
    public CartAggregate() {
    }
    
    @CommandHandler
    public CartAggregate(CartRemoveCommand command) {
    	//validate command
        log.info("Executing  CardRemoveCommand for " +
                "User Id: {}",
                command.getUserid());
        
    	CartRemovedEvent event = new CartRemovedEvent();
    	
    	BeanUtils.copyProperties(command, event);
    	
    	AggregateLifecycle.apply(event);
    	
        log.info("CardRemovedEvent Applied"); 
    }
    
    @EventSourcingHandler
    public void on(CartRemovedEvent event) {
    	this.cart = event.getCart();
    	this.orderid = event.getOrderid();
    	this.cartstatus = event.getCardstatus();

  
    }
    
	//cancel
    @CommandHandler
    public void handle(CartCancelCommand command) {
    	
        CartCancelledEvent event = new CartCancelledEvent();
        
        BeanUtils.copyProperties(command, event);    
         
        AggregateLifecycle.apply(event);
        
    }

    @EventSourcingHandler
    public void on(CartCancelledEvent event) {
    	
    }

    

    
    
    
    
}
