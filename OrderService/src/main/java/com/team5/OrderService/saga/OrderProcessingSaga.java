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

import com.team5.CommonService.command.CancelUserBalanceCommand;
import com.team5.CommonService.command.CancleOrderCommand;
import com.team5.CommonService.command.CanclePaymentCommand;
import com.team5.CommonService.command.CompleteOrderCommand;
import com.team5.CommonService.command.EditCartCommand;
import com.team5.CommonService.command.EditStockCommand;
import com.team5.CommonService.command.ExcludingBalanceCommand;
import com.team5.CommonService.command.ShipOrderCommand;
import com.team5.CommonService.command.ValidatePaymentCommand;
import com.team5.CommonService.events.CancelUserBalanceEvent;
import com.team5.CommonService.events.EditCartEvent;
import com.team5.CommonService.events.EditStockEvent;
import com.team5.CommonService.events.ExcludedBalanceEvent;
import com.team5.CommonService.events.OrderCancelledEvent;
import com.team5.CommonService.events.OrderCompletedEvent;
import com.team5.CommonService.events.OrderShippedEvent;
import com.team5.CommonService.events.PaymentCanceledEvent;
import com.team5.CommonService.events.PaymentProcessedEvent;
import com.team5.CommonService.model.OrderCommon;
import com.team5.CommonService.model.User1;
import com.team5.CommonService.queries.GetOrderQuery;
import com.team5.CommonService.queries.GetUserBalanceQuery;
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
        
        GetUserBalanceQuery getUserBalanceQuery = new GetUserBalanceQuery();
        getUserBalanceQuery.setUser(event.getUser());
        
        User1 user = null;
        
        //catch if turn off user service or user invalid
        try {
            user = queryGateway.query(getUserBalanceQuery,ResponseTypes.instanceOf(User1.class)).join();
           

        } catch (Exception e) {
            log.error(e.getMessage());
            //Start the Compensating transaction
            cancelOrderCommand(event.getOrderid());
            
        }
        
        //
        if (user.getBalance() >= event.getTotal()) {
        	
        	try {
            	// if true throw
                //if(true)
                //    throw new Exception();
            	
            	ValidatePaymentCommand validatePaymentCommand = new ValidatePaymentCommand();
                validatePaymentCommand.setPaymentid(UUID.randomUUID().toString());
                validatePaymentCommand.setOrderid(event.getOrderid());
                
                validatePaymentCommand.setTotal(event.getTotal());
                validatePaymentCommand.setUser(event.getUser());
                
                commandGateway.sendAndWait(validatePaymentCommand);  	
            	
            } catch (Exception e) {
            	log.error(e.getMessage());
            	
            	cancelOrderCommand(event.getOrderid());
            	
            }
        	
        }
        else {
        	log.info("UserBalance's not enough");
        	
        	cancelOrderCommand(event.getOrderid());
        }
        
  
	}  
	
    @SagaEventHandler(associationProperty = "orderid")
    private void handle(PaymentProcessedEvent event) {
        
        log.info("PaymentProcessedEvent in Saga for Order Id : {}",
                event.getOrderid()); 
        
        try {
            ExcludingBalanceCommand excludingBalanceCommand = new ExcludingBalanceCommand();
            excludingBalanceCommand.setUser(event.getUser());
            excludingBalanceCommand.setOrderid(event.getOrderid());
            excludingBalanceCommand.setTotal(event.getTotal());
            excludingBalanceCommand.setId(UUID.randomUUID().toString());
            
            commandGateway.sendAndWait(excludingBalanceCommand);
        } catch (Exception e) {
            log.error(e.getMessage());
            // Start the compensating transaction
            log.error("Cannot connect to Payment"); 
            cancelPaymentCommand(event);
            
        }


    }

    
    @SagaEventHandler(associationProperty = "orderid")
    private void handle(ExcludedBalanceEvent event) {
        
        log.info("ExcludedBalanceEvent in Saga for Order Id : {}",
                event.getOrderid()); 
        
        try {
        	//if true throw
            //if(true)
            //    throw new Exception();


            ShipOrderCommand shipOrderCommand = new ShipOrderCommand();
            shipOrderCommand.setShipmentid(UUID.randomUUID().toString());
            shipOrderCommand.setOrderid(event.getOrderid());
            shipOrderCommand.setUser(event.getUser());
            
            commandGateway.sendAndWait(shipOrderCommand);
        } catch (Exception e) {
            log.error(e.getMessage());
            // Start the compensating transaction
            log.error("Cannot connect to ShipService"); 
            cancelUserBalanceCommand(event);
            
        }
        
        
    }
    
    
    
    @SagaEventHandler(associationProperty = "orderid")
    private void handle(OrderShippedEvent event) {
    	
        log.info("OrderShippedEvent in Saga for Order Id : {}",
                event.getOrderid());
        
        /*GetOrderQuery getOrderQuery = new GetOrderQuery();
        getOrderQuery.setOrderid(event.getOrderid());
        
        
        OrderCommon ordercommon = null;
        
        //
        try {
        	ordercommon = queryGateway.query(getOrderQuery,ResponseTypes.instanceOf(OrderCommon.class)).join();
           

        } catch (Exception e) {
            log.error(e.getMessage());
            //Start the Compensating transaction
            cancelOrderCommand(event.getOrderid());
            
        }*/
        
    	try {
        	// if true throw
            //if(true)
            //    throw new Exception();
        	
    		EditCartCommand editCartCommand = new EditCartCommand();
    		editCartCommand.setCardid(UUID.randomUUID().toString());
    		editCartCommand.setUser(event.getUser());
    		editCartCommand.setCardstatus("ORDERED");
    		editCartCommand.setOrderid(event.getOrderid());
            
            
            commandGateway.sendAndWait(editCartCommand);  	
        	
        } catch (Exception e) {
        	log.error(e.getMessage());
        	
        	cancelOrderCommand(event.getOrderid());
        	
        }
             
    }
    
    @SagaEventHandler(associationProperty = "orderid")
    private void handle(EditCartEvent event) {
        
        log.info("EdittedCardEvent in Saga for User Id : {}",
                event.getUser()); 
        
        GetOrderQuery getOrderQuery = new GetOrderQuery();
        getOrderQuery.setOrderid(event.getOrderid());
        
        
        OrderCommon ordercommon = null;
        
        //
        try {
        	ordercommon = queryGateway.query(getOrderQuery,ResponseTypes.instanceOf(OrderCommon.class)).join();
        } catch (Exception e) {
            log.error(e.getMessage());
            //Start the Compensating transaction
            cancelOrderCommand(event.getOrderid());
            
        }
        
        EditStockCommand editStockCommand = new EditStockCommand();
        editStockCommand.setLineitems(ordercommon.getLineitems());
        editStockCommand.setOrderid(event.getOrderid());
        
        commandGateway.sendAndWait(editStockCommand);
        
    }
    
    @SagaEventHandler(associationProperty = "orderid")
    private void handle(EditStockEvent event) {
    	
        log.info("EditStockEvent in Saga for Order Id : {}",
                event.getOrderid());
        
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

    @SagaEventHandler(associationProperty = "orderid")
    public void handle(CancelUserBalanceEvent event) {
    	log.info("CancelUserBalanceEvent in Saga for Order Id : {}",
                event.getOrderid());
    	cancelPaymentCommand(event);
    }
    
    //------------------------------------------Cancel Function---------------------------------------------------
    //cancle order
    private void cancelOrderCommand(String orderid) {
        CancleOrderCommand cancelOrderCommand  = new CancleOrderCommand();
        cancelOrderCommand.setOrderid(orderid);
        
        commandGateway.send(cancelOrderCommand);
    }
    //cancle payment
    private void cancelPaymentCommand(PaymentProcessedEvent event) {
        CanclePaymentCommand canclePaymentCommand = new CanclePaymentCommand();
        canclePaymentCommand.setPaymentid(event.getPaymentid());
        canclePaymentCommand.setOrderid(event.getOrderid());
        
        commandGateway.send(canclePaymentCommand);
    }
    private void cancelPaymentCommand(CancelUserBalanceEvent event) {
        CanclePaymentCommand canclePaymentCommand = new CanclePaymentCommand();
        canclePaymentCommand.setOrderid(event.getOrderid());
        commandGateway.send(canclePaymentCommand);
    }
    private void cancelUserBalanceCommand(ExcludedBalanceEvent event) {
        CancelUserBalanceCommand cancelUserBalanceCommand= new CancelUserBalanceCommand();
        cancelUserBalanceCommand.setOrderid(event.getOrderid());
        cancelUserBalanceCommand.setUserid(event.getUser());
        cancelUserBalanceCommand.setTotal(event.getTotal());
        commandGateway.send(cancelUserBalanceCommand);
    }
    
    
    
}
