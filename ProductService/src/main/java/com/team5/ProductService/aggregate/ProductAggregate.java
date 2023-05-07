package com.team5.ProductService.aggregate;

import java.util.List;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.team5.CommonService.command.EditStockCommand;
import com.team5.CommonService.command.ShipOrderCommand;
import com.team5.CommonService.events.EditStockEvent;
import com.team5.CommonService.events.OrderShippedEvent;
import com.team5.CommonService.model.LineItems;

@Aggregate
public class ProductAggregate {
	private static Logger log = LoggerFactory.getLogger(ProductAggregate.class);


	@AggregateIdentifier
	private List<LineItems> lineitems;
	private String orderid;
	
    public ProductAggregate() {
    }
    
    @CommandHandler
    public ProductAggregate(EditStockCommand editStockCommand) {
    	//validata command
    	//publish order shipped event
        log.info("Executing  EditStockCommand for " +
                "Order Id: {}",
                editStockCommand.getOrderid());
        
        EditStockEvent editStockEvent = new EditStockEvent();
        editStockEvent.setLineitems(editStockCommand.getLineitems());
        editStockEvent.setOrderid(editStockCommand.getOrderid());
    		
    	AggregateLifecycle.apply(editStockEvent);
    	
        log.info("EditStockEvent Applied"); 
    	
    }
    
    @EventSourcingHandler
    public void on(EditStockEvent event) {
    	this.lineitems = event.getLineitems();
    	this.orderid = event.getOrderid();
    }
}
