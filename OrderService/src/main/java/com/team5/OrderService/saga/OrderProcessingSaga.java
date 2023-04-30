package com.team5.OrderService.saga;

import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.spring.stereotype.Saga;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.team5.CommonService.command.CancleOrderCommand;
import com.team5.CommonService.command.CanclePaymentCommand;
import com.team5.CommonService.command.CompleteOrderCommand;
import com.team5.CommonService.command.ShipOrderCommand;
import com.team5.CommonService.command.ValidatePaymentCommand;
import com.team5.CommonService.events.OrderCancelledEvent;
import com.team5.CommonService.events.OrderCompletedEvent;
import com.team5.CommonService.events.OrderShippedEvent;
import com.team5.CommonService.events.PaymentCanceledEvent;
import com.team5.CommonService.events.PaymentProcessedEvent;
import com.team5.CommonService.model.User;
import com.team5.CommonService.queries.GetUserPaymentDetailsQuery;
import com.team5.OrderService.event.OrderCreatedEvent;


@Saga
public class OrderProcessingSaga {
	private static Logger log = LoggerFactory.getLogger(OrderProcessingSaga.class);
	
	@Autowired
	private transient CommandGateway commandGateway;
	
	@Autowired
	private transient QueryGateway queryGateway;
	
	@StartSaga
	@SagaEventHandler(associationProperty = "orderid")
	private void handle(OrderCreatedEvent event) {
        log.info("OrderCreatedEvent in Saga for Order Id : {}",
                event.getOrderid());
        
        GetUserPaymentDetailsQuery getUserPaymentDetailsQuery = new GetUserPaymentDetailsQuery();
        getUserPaymentDetailsQuery.setUser(event.getUser());
        
        User user = null;
        
        try {
            user = queryGateway.query(
                    getUserPaymentDetailsQuery,
                    ResponseTypes.instanceOf(User.class)
            ).join();

        } catch (Exception e) {
            log.error(e.getMessage());
            //Start the Compensating transaction
            cancelOrderCommand(event.getOrderid());
            
        }
		
        ValidatePaymentCommand validatePaymentCommand = new ValidatePaymentCommand();
        validatePaymentCommand.setPaymentid(UUID.randomUUID().toString());
        validatePaymentCommand.setOrderid(event.getOrderid());
        //validatePaymentCommand.setCartdetails(user.getCartdetails());
        
        commandGateway.sendAndWait(validatePaymentCommand);
	}
    //cancle order
    private void cancelOrderCommand(String orderid) {
        CancleOrderCommand cancelOrderCommand  = new CancleOrderCommand(orderid);
        commandGateway.send(cancelOrderCommand);
    }
    
    
    
    
    
    
	
    @SagaEventHandler(associationProperty = "orderid")
    private void handle(PaymentProcessedEvent event) {
        
        log.info("PaymentProcessedEvent in Saga for Order Id : {}",
                event.getOrderid()); 
        
        try {
            if(true)
                throw new Exception();

            ShipOrderCommand shipOrderCommand = new ShipOrderCommand();
            shipOrderCommand.setShipmentid(UUID.randomUUID().toString());
            shipOrderCommand.setOrderid(event.getOrderid());
            
            commandGateway.send(shipOrderCommand);
        } catch (Exception e) {
            log.error(e.getMessage());
            // Start the compensating transaction
            cancelPaymentCommand(event);
        }

    }
    //cancle payment
    private void cancelPaymentCommand(PaymentProcessedEvent event) {
        CanclePaymentCommand canclePaymentCommand = new CanclePaymentCommand(event.getPaymentid(), event.getOrderid());
        
        commandGateway.send(canclePaymentCommand);
    }
    
    
    
    
    
    @SagaEventHandler(associationProperty = "orderid")
    private void handle(OrderShippedEvent event) {
        log.info("OrderShippedEvent in Saga for Order Id : {}",
                event.getOrderid());
        
        CompleteOrderCommand completeOrderCommand = new CompleteOrderCommand();
        completeOrderCommand.setOrderid(event.getOrderid());
        completeOrderCommand.setOrderstatus("APPROVED");
        
        commandGateway.send(completeOrderCommand);
        

    }
    
    
    
    
    
    @SagaEventHandler(associationProperty = "orderid")
    @EndSaga
    public void handle(OrderCompletedEvent event) {
        log.info("OrderCompletedEvent in Saga for Order Id : {}",
                event.getOrderid());
    }
    
    @SagaEventHandler(associationProperty = "orderid")
    @EndSaga
    public void handle(OrderCancelledEvent event) {
        log.info("OrderCancelledEvent in Saga for Order Id : {}",
                event.getOrderid());
    }
    
    
    
    @SagaEventHandler(associationProperty = "orderid")
    public void handle(PaymentCanceledEvent event) {
        log.info("PaymentCancelledEvent in Saga for Order Id : {}",
                event.getOrderid());
        cancelOrderCommand(event.getOrderid());
    }


}
