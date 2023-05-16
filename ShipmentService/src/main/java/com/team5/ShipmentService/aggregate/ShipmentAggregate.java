package com.team5.ShipmentService.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.team5.CommonService.command.ShipmentCancelCommand;
import com.team5.CommonService.command.ShipmentCreateCommand;
import com.team5.CommonService.events.ShipmentCreatedEvent;
import com.team5.CommonService.events.ShipmentCancelledEvent;

@Aggregate
public class ShipmentAggregate {
	
	private static Logger log = LoggerFactory.getLogger(ShipmentAggregate.class);

	@AggregateIdentifier
	private String shipmentid;
	private String orderid;
	private String shipmentstatus;
	
    public ShipmentAggregate() {
    }
    
    @CommandHandler
    public ShipmentAggregate(ShipmentCreateCommand command) {
    	//validata command
    	//publish order shipped event
        log.info("Executing  ShipmentCreateCommand for " +
                "Order Id: {}",
                command.getOrderid());
        
    	ShipmentCreatedEvent event = new ShipmentCreatedEvent();
    	event.setShipmentid(command.getShipmentid());
    	event.setOrderid(command.getOrderid());
    	event.setShipmentstatus(command.getShipmentstatus());
    	
    	//
    	event.setUser(command.getUser());
    	event.setPaymentid(command.getPaymentid());
    		
    	AggregateLifecycle.apply(event);
    	
        log.info("OrderShippedEvent Applied"); 
    	
    }
    
    @EventSourcingHandler
    public void on(ShipmentCreatedEvent event) {
    	this.shipmentid = event.getShipmentid();
    	this.orderid = event.getOrderid();
    	this.shipmentstatus = event.getShipmentstatus();
    }
    
    
	//cancel
    @CommandHandler
    public void handle(ShipmentCancelCommand command) {
        ShipmentCancelledEvent event = new ShipmentCancelledEvent();
        event.setShipmentid(command.getShipmentid());
        event.setOrderid(command.getOrderid());
        event.setShipmentstatus(command.getShipmentstatus());
       
        //
        event.setUser(command.getUser());
        event.setPaymentid(command.getPaymentid());
         
        AggregateLifecycle.apply(event);
        
    }

    @EventSourcingHandler
    public void on(ShipmentCancelledEvent event) {
        this.shipmentstatus = event.getShipmentstatus();
    }
    

}
