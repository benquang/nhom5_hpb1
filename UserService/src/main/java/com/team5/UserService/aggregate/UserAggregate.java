package com.team5.UserService.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.team5.CommonService.command.BalanceExcludeCommand;
import com.team5.CommonService.command.UserCancelCommand;
import com.team5.CommonService.command.PaymentCancelCommand;
import com.team5.CommonService.command.ShipmentCreateCommand;
import com.team5.CommonService.events.UserCanceledEvent;
import com.team5.CommonService.events.BalanceExcludedEvent;
import com.team5.CommonService.events.ShipmentCreatedEvent;
import com.team5.CommonService.events.PaymentCanceledEvent;

@Aggregate
public class UserAggregate {

	private static Logger log = LoggerFactory.getLogger(UserAggregate.class);
	
	@AggregateIdentifier
	private String user;
	
    public UserAggregate() {
    }
    
    @CommandHandler
    public UserAggregate(BalanceExcludeCommand command) {
    	
        log.info("Executing BalanceExcludeCommand for Order Id: {}, User Id: {}, Excluding - : {}",
                command.getOrderid(), command.getUserid(), command.getTotal());
        
    	BalanceExcludedEvent event = new BalanceExcludedEvent();
    	event.setUser(command.getUser());
    	event.setOrderid(command.getOrderid());
    	event.setUserid(command.getUserid());
    	event.setTotal(command.getTotal());
    		
    	AggregateLifecycle.apply(event);
    	
        log.info("ExcludedBalanceEvent Applied"); 
    	
    }
    
    @EventSourcingHandler
    public void on(BalanceExcludedEvent event) {
    	this.user = event.getUser();
    }
    
    
	//cancel
    @CommandHandler
    public void handle(UserCancelCommand command) {

        UserCanceledEvent event = new UserCanceledEvent();
        event.setUser(command.getUser());
        event.setOrderid(command.getOrderid());
         
        AggregateLifecycle.apply(event);
        
    }

    @EventSourcingHandler
    public void on(UserCanceledEvent event) {
    	
    }
}
