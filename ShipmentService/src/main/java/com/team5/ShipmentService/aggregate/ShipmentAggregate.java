package com.team5.ShipmentService.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import com.team5.CommonService.command.ShipOrderCommand;
import com.team5.CommonService.events.OrderShippedEvent;

@Aggregate
public class ShipmentAggregate {

	@AggregateIdentifier
	private String shipmentid;
	private String orderid;
	private String shipmentstatus;
	
    public ShipmentAggregate() {
    }
    
    @CommandHandler
    public ShipmentAggregate(ShipOrderCommand shipOrderCommand) {
    	//validata command
    	//publish order shipped event
    	OrderShippedEvent orderShippedEvent = new OrderShippedEvent();
    	orderShippedEvent.setShipmentid(shipOrderCommand.getShipmentid());
    	orderShippedEvent.setOrderid(shipOrderCommand.getOrderid());
    	orderShippedEvent.setShipmentstatus("COMPLETED");
    		
    	AggregateLifecycle.apply(orderShippedEvent);
    	
    }
    
    @EventSourcingHandler
    public void on(OrderShippedEvent event) {
    	this.shipmentid = event.getShipmentid();
    	this.orderid = event.getOrderid();
    	this.shipmentstatus = event.getShipmentstatus();
    }
}
