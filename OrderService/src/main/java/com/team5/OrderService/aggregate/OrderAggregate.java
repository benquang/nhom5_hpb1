package com.team5.OrderService.aggregate;

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
import com.team5.CommonService.events.OrderCancelledEvent;
import com.team5.CommonService.events.OrderCompletedEvent;
import com.team5.CommonService.model.LineItems;
import com.team5.OrderService.command.OrderCreateCommand;
import com.team5.OrderService.event.OrderCreatedEvent;

@Aggregate
public class OrderAggregate {
	private static Logger log = LoggerFactory.getLogger(OrderAggregate.class);
	
	@AggregateIdentifier
	private String orderid;
	private String user;
	private String fullname;
	private String phone;
	private String email;
	private String address;
	private String ordernote;
	private List<LineItems> lineitems;
	private Double total;
	private String orderstatus;
	
    public OrderAggregate() {
    }
    
    @CommandHandler
    public OrderAggregate(OrderCreateCommand command) {
    	
    	OrderCreatedEvent event = new OrderCreatedEvent();
    	
    	BeanUtils.copyProperties(command, event);
    	
    	AggregateLifecycle.apply(event);
    	
    }
    
    @EventSourcingHandler
    public void on(OrderCreatedEvent event) {
    	this.orderid = event.getOrderid();
    	this.user = event.getUser();
    	this.fullname = event.getFullname();
    	this.phone = event.getPhone();
    	this.email = event.getEmail();
    	this.address = event.getAddress();
    	this.ordernote = event.getOrdernote();
    	this.lineitems = event.getLineitems();
    	this.total = event.getTotal();
    	this.orderstatus = event.getOrderstatus();	
    }
    
    @CommandHandler
    public void handle(OrderCompleteCommand completeOrderCommand) {
        //Validate The Command
        // Publish Order Completed Event
    	OrderCompletedEvent orderCompletedEvent = new OrderCompletedEvent();
    	orderCompletedEvent.setOrderid(completeOrderCommand.getOrderid());
    	orderCompletedEvent.setOrderstatus(completeOrderCommand.getOrderstatus());
    	
    	AggregateLifecycle.apply(orderCompletedEvent);
        
    }
    
    @EventSourcingHandler
    public void on(OrderCompletedEvent event) {
        this.orderstatus = event.getOrderstatus();
    }
    
    
    @CommandHandler
    public void handle(OrderCancelCommand command) {
    	OrderCancelledEvent event = new OrderCancelledEvent();
    	
    	BeanUtils.copyProperties(command, event);
    	
    	AggregateLifecycle.apply(event);
    	
    }

    @EventSourcingHandler
    public void on(OrderCancelledEvent event) {
    	this.orderstatus = event.getOrderstatus();
    }
    
    
    
    
}
