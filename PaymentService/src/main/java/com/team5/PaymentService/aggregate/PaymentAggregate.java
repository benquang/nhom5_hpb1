package com.team5.PaymentService.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.team5.CommonService.command.PaymentCancelCommand;
import com.team5.CommonService.command.PaymentCreateCommand;
import com.team5.CommonService.events.PaymentCanceledEvent;
import com.team5.CommonService.events.PaymentCreatedEvent;

@Aggregate
public class PaymentAggregate {
	private static Logger log = LoggerFactory.getLogger(PaymentAggregate.class);
	
	@AggregateIdentifier
	private String paymentid;
	private String orderid;
	private String paymentstatus;
	//
	
	public PaymentAggregate() {}
	
	@CommandHandler
	public PaymentAggregate(PaymentCreateCommand command) {
		//validate payment details
		//publish payment processed event
        log.info("Executing PaymentCreateCommand for " +
                "Order Id: {} and Payment Id: {}",
                command.getOrderid(),
                command.getPaymentid());
        
        PaymentCreatedEvent event = new PaymentCreatedEvent();
        event.setOrderid(command.getOrderid());
        event.setPaymentid(command.getPaymentid());
        event.setPaymentstatus(command.getPaymentstatus());  
        
        //user
        event.setUser(command.getUser());
        //event.setUserid(command.getUserid());
        
        
        AggregateLifecycle.apply(event);
        
        log.info("PaymentCreatedEvent Applied");   
	}
	
	@EventSourcingHandler
	public void on(PaymentCreatedEvent event) {
		this.paymentid = event.getPaymentid();
		this.orderid = event.getOrderid();
		this.paymentstatus = event.getPaymentstatus();
	}
	
	//cancel
    @CommandHandler
    public void handle(PaymentCancelCommand command) {
        PaymentCanceledEvent event = new PaymentCanceledEvent();
        event.setPaymentid(command.getPaymentid());
        event.setOrderid(command.getOrderid());
        event.setPaymentstatus(command.getPaymentstatus());
        
        //
        event.setUser(command.getUser());
         
        AggregateLifecycle.apply(event);
        
        /*log.info("event id " +
                "Order Id: {}",
                paymentCanceledEvent.getOrderid());*/
    }

    @EventSourcingHandler
    public void on(PaymentCanceledEvent event) {
        this.paymentstatus = event.getPaymentstatus();
    }
}
