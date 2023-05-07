package com.team5.ShipmentService.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.team5.CommonService.command.ShipOrderCommand;
import com.team5.CommonService.events.OrderShippedEvent;

@Aggregate
public class ShipmentAggregate {
	private static Logger log = LoggerFactory.getLogger(ShipmentAggregate.class);


	@AggregateIdentifier
	private String shipmentid;
	private String orderid;
	private String shipmentstatus;
	private String user;
	
    public ShipmentAggregate() {
    }
    
    @CommandHandler
    public ShipmentAggregate(ShipOrderCommand shipOrderCommand) {
    	//validata command
    	//publish order shipped event
        log.info("Executing  ShipOrderCommand for " +
                "Order Id: {}",
                shipOrderCommand.getOrderid());
    	OrderShippedEvent orderShippedEvent = new OrderShippedEvent();
    	orderShippedEvent.setShipmentid(shipOrderCommand.getShipmentid());
    	orderShippedEvent.setOrderid(shipOrderCommand.getOrderid());
    	orderShippedEvent.setShipmentstatus("COMPLETED");
    	orderShippedEvent.setUser(shipOrderCommand.getUser());
    		
    	AggregateLifecycle.apply(orderShippedEvent);
    	
        log.info("OrderShippedEvent Applied"); 
    	
    }
    
    @EventSourcingHandler
    public void on(OrderShippedEvent event) {
    	this.shipmentid = event.getShipmentid();
    	this.orderid = event.getOrderid();
    	this.shipmentstatus = event.getShipmentstatus();
    	this.user = event.getUser();
    }
}
