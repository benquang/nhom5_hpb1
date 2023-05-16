package com.team5.ProductService.aggregate;

import java.util.List;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.team5.CommonService.command.CartCancelCommand;
import com.team5.CommonService.command.ProductCancelCommand;
import com.team5.CommonService.command.ProductExcludeCommand;
import com.team5.CommonService.command.ShipmentCreateCommand;
import com.team5.CommonService.events.CartCancelledEvent;
import com.team5.CommonService.events.ProductCancelledEvent;
import com.team5.CommonService.events.ProductExcludedEvent;
import com.team5.CommonService.events.ShipmentCreatedEvent;
import com.team5.CommonService.model.LineItems;

@Aggregate
public class ProductAggregate {
	private static Logger log = LoggerFactory.getLogger(ProductAggregate.class);


	@AggregateIdentifier
	private String product;
	private List<LineItems> lineitems;
	private String orderid;
	
    public ProductAggregate() {
    }
    
    @CommandHandler
    public ProductAggregate(ProductExcludeCommand command) {
    	//validata command
    	//publish order shipped event
        log.info("Executing  ProductExcludeCommand for " +
                "Order Id: {}",
                command.getOrderid());
        
        ProductExcludedEvent event = new ProductExcludedEvent();
        event.setProduct(command.getProduct());
        event.setLineitems(command.getLineitems());
        event.setOrderid(command.getOrderid());
        
        //
        event.setUser(command.getUser());
        event.setPaymentid(command.getPaymentid());
        event.setShipmentid(command.getShipmentid());
        event.setCart(command.getCart());
    		
    	AggregateLifecycle.apply(event);
    	
        log.info("ProductExcludedEvent Applied"); 
    	
    }
    
    @EventSourcingHandler
    public void on(ProductExcludedEvent event) {
    	this.product = event.getProduct();
    	this.lineitems = event.getLineitems();
    	this.orderid = event.getOrderid();
    }
    
	//cancel
    @CommandHandler
    public void handle(ProductCancelCommand command) {
    	
        ProductCancelledEvent event = new ProductCancelledEvent();
        
        BeanUtils.copyProperties(command, event);    
         
        AggregateLifecycle.apply(event);
        
    }

    @EventSourcingHandler
    public void on(ProductCancelledEvent event) {
    	
    }
}
