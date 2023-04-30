package com.team5.PaymentService.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.team5.CommonService.command.CanclePaymentCommand;
import com.team5.CommonService.command.ValidatePaymentCommand;
import com.team5.CommonService.events.PaymentCanceledEvent;
import com.team5.CommonService.events.PaymentProcessedEvent;

@Aggregate
public class PaymentAggregate {
	private static Logger log = LoggerFactory.getLogger(PaymentAggregate.class);
	
	@AggregateIdentifier
	private String paymentid;
	private String orderid;
	private String paymentstatus;
	
	public PaymentAggregate() {}
	
	@CommandHandler
	public PaymentAggregate(ValidatePaymentCommand validatePaymentCommand) {
		//validate payment details
		//publish payment processed event
        log.info("Executing ValidatePaymentCommand for " +
                "Order Id: {} and Payment Id: {}",
                validatePaymentCommand.getOrderid(),
                validatePaymentCommand.getPaymentid());
        
        PaymentProcessedEvent paymentProcessedEvent = new PaymentProcessedEvent();
        paymentProcessedEvent.setOrderid(validatePaymentCommand.getOrderid());
        paymentProcessedEvent.setPaymentid(validatePaymentCommand.getPaymentid());
        
        AggregateLifecycle.apply(paymentProcessedEvent);
        
        log.info("PaymentProcessedEvent Applied");   
	}
	
	@EventSourcingHandler
	public void on(PaymentProcessedEvent event) {
		this.paymentid = event.getPaymentid();
		this.orderid = event.getOrderid();
	}
	
	//cancel
    @CommandHandler
    public void handle(CanclePaymentCommand cancelPaymentCommand) {
        PaymentCanceledEvent paymentCanceledEvent = new PaymentCanceledEvent();
        
        BeanUtils.copyProperties(cancelPaymentCommand, paymentCanceledEvent);
         
        AggregateLifecycle.apply(paymentCanceledEvent);
    }

    @EventSourcingHandler
    public void on(PaymentCanceledEvent event) {
        this.paymentstatus = event.getPaymentstatus();
    }
}
