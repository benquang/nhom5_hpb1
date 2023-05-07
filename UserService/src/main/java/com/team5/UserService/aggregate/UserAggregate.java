package com.team5.UserService.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.team5.CommonService.command.ExcludingBalanceCommand;
import com.team5.CommonService.command.ShipOrderCommand;
import com.team5.CommonService.events.ExcludedBalanceEvent;
import com.team5.CommonService.events.OrderShippedEvent;

@Aggregate
public class UserAggregate {

	private static Logger log = LoggerFactory.getLogger(UserAggregate.class);
	@AggregateIdentifier
	private String id;
	private String user;
	private String orderid;
	private Double total;
	
    public UserAggregate() {
    }
    
    @CommandHandler
    public UserAggregate(ExcludingBalanceCommand excludingBalanceCommand) {
    	//validata command
    	//publish order shipped event
        log.info("Executing  ExcludingBalanceCommand for " +
                "User Id: {}",
                excludingBalanceCommand.getUser());
        
    	ExcludedBalanceEvent excludedBalanceEvent = new ExcludedBalanceEvent();
    	excludedBalanceEvent.setId(excludingBalanceCommand.getId());
    	excludedBalanceEvent.setUser(excludingBalanceCommand.getUser());
    	excludedBalanceEvent.setOrderid(excludingBalanceCommand.getOrderid());
    	excludedBalanceEvent.setTotal(excludingBalanceCommand.getTotal());
    		
    	AggregateLifecycle.apply(excludedBalanceEvent);
    	
        log.info("ExcludedBalanceEvent Applied"); 
    	
    }
    
    @EventSourcingHandler
    public void on(ExcludedBalanceEvent event) {
    	this.id = event.getId();
    	this.user = event.getUser();
    	this.orderid = event.getOrderid();
    	this.total = event.getTotal();
    }
}
