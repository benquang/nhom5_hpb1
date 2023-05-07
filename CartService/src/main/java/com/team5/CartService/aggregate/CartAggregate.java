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

import com.team5.CommonService.command.CancleOrderCommand;
import com.team5.CommonService.command.CompleteOrderCommand;
import com.team5.CommonService.command.EditCartCommand;
import com.team5.CommonService.events.EditCartEvent;
import com.team5.CommonService.events.OrderCancelledEvent;
import com.team5.CommonService.events.OrderCompletedEvent;

@Aggregate
public class CartAggregate {
	private static Logger log = LoggerFactory.getLogger(CartAggregate.class);
	
	@AggregateIdentifier
	private String cardid;
	private String user;
	private String orderid;
	private String cartstatus;
	
    public CartAggregate() {
    }
    
    @CommandHandler
    public CartAggregate(EditCartCommand editCartCommand) {
    	//validate command
        log.info("Executing  EditCartCommand for " +
                "User Id: {}",
                editCartCommand.getUser());
        
    	EditCartEvent editCartEvent = new EditCartEvent();
    	
    	BeanUtils.copyProperties(editCartCommand, editCartEvent);
    	
    	AggregateLifecycle.apply(editCartEvent);
    	
    	/*log.info("line items " +
                "Order Id: {}",
                orderCreatedEvent.getLineitems().get(0).getProduct());*/
    	
        log.info("EditCartEvent Applied"); 
    }
    
    @EventSourcingHandler
    public void on(EditCartEvent event) {
    	this.cardid = event.getCardid();
    	this.user = event.getUser();
    	this.orderid = event.getOrderid();
    	this.cartstatus = event.getCardstatus();

  
    }
    

    

    
    
    
    
}
