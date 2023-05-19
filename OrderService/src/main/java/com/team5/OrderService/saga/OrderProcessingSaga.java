package com.team5.OrderService.saga;

import java.util.List;
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

import com.team5.CommonService.command.BalanceExcludeCommand;
import com.team5.CommonService.command.CartCancelCommand;
import com.team5.CommonService.command.UserCancelCommand;
import com.team5.CommonService.command.OrderCancelCommand;
import com.team5.CommonService.command.PaymentCancelCommand;
import com.team5.CommonService.command.OrderCompleteCommand;
import com.team5.CommonService.command.CartRemoveCommand;
import com.team5.CommonService.command.PaymentCreateCommand;
import com.team5.CommonService.command.ProductCancelCommand;
import com.team5.CommonService.command.ProductExcludeCommand;
import com.team5.CommonService.command.ShipmentCancelCommand;
import com.team5.CommonService.command.ShipmentCreateCommand;
import com.team5.CommonService.events.UserCanceledEvent;
import com.team5.CommonService.events.BalanceExcludedEvent;
import com.team5.CommonService.events.CartCancelledEvent;
import com.team5.CommonService.events.CartRemovedEvent;
import com.team5.CommonService.events.ProductExcludedEvent;
import com.team5.CommonService.events.OrderCancelledEvent;
import com.team5.CommonService.events.OrderCompletedEvent;
import com.team5.CommonService.events.ShipmentCreatedEvent;
import com.team5.CommonService.events.PaymentCanceledEvent;
import com.team5.CommonService.events.PaymentCreatedEvent;
import com.team5.CommonService.events.ProductCancelledEvent;
import com.team5.CommonService.events.ShipmentCancelledEvent;
import com.team5.CommonService.model.LineItems;
import com.team5.CommonService.model.OrderCommon;
import com.team5.CommonService.model.ProductCommon;
import com.team5.CommonService.model.UserCommon;
import com.team5.CommonService.queries.GetOrderQuery;
import com.team5.CommonService.queries.GetProductQuery;
import com.team5.CommonService.queries.GetUserBalanceQuery;
import com.team5.CommonService.queries.GetUserCardQuery;
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
        
        GetUserBalanceQuery query = new GetUserBalanceQuery();
        query.setUser(event.getUser());
        
        UserCommon user = null;
        
        //catch if turn off user service or user invalid
        try {
            user = queryGateway.query(query,ResponseTypes.instanceOf(UserCommon.class)).join();  
            
            
        } catch (Exception e) {
        	
            log.error(e.getMessage());
            //Start the Compensating transaction
            cancelOrder(event.getOrderid());          
        }
        
        //
        if (user.getBalance() >= event.getTotal()) {
        	
        	BalanceExcludeCommand command = new BalanceExcludeCommand();
        	command.setUser(UUID.randomUUID().toString());
        	
        	command.setOrderid(event.getOrderid());
        	command.setUserid(user.getUserid());
        	command.setTotal(event.getTotal());
        	
        	
            commandGateway.sendAndWait(command);
        	      	
        }
        else {
        	log.info("UserBalance's not enough");
        	
        	cancelOrder(event.getOrderid());
        }
        
  
	}
    //cancle order
    private void cancelOrder(String orderid) {
        OrderCancelCommand command  = new OrderCancelCommand();
        command.setOrderid(orderid);
        
        commandGateway.send(command);
    }
    
    
    @SagaEventHandler(associationProperty = "orderid")
    private void handle(BalanceExcludedEvent event) {
        
        log.info("BalanceExcludedEvent in Saga for Order Id : {}, User Id: {}, Total: {}",
                event.getOrderid(), event.getUserid(), event.getTotal()); 
        
        try {
        	
        	PaymentCreateCommand command = new PaymentCreateCommand();
        	command.setPaymentid(UUID.randomUUID().toString());
        	command.setOrderid(event.getOrderid());
        	command.setPaymentstatus("COMPLETED");
        	
        	//
        	command.setUser(event.getUser());
            
            commandGateway.sendAndWait(command);
        } catch (Exception e) {
            log.error(e.getMessage()); 
            cancelUser(event.getUser(), event.getOrderid());
            
        }     
    }
    //cancle user
    private void cancelUser(String user, String orderid) {
    	
        UserCancelCommand command = new UserCancelCommand();
        command.setUser(user);
        command.setOrderid(orderid);
        
        commandGateway.send(command);
        
    }
    
    @SagaEventHandler(associationProperty = "orderid")
    private void handle(PaymentCreatedEvent event) {
        
        log.info("PaymentCreatedEvent in Saga for Order Id : {}",
                event.getOrderid()); 
        
        try {
        	//
        	ShipmentCreateCommand command = new ShipmentCreateCommand();
        	command.setShipmentid(UUID.randomUUID().toString());
        	command.setOrderid(event.getOrderid());
        	command.setShipmentstatus("COMPLETE");
        	
        	//
        	command.setUser(event.getUser());
        	command.setPaymentid(event.getPaymentid());

            
            commandGateway.sendAndWait(command);
            
        } catch (Exception e) {
            log.error(e.getMessage());
            // Start the compensating transaction
            cancelPayment(event.getPaymentid(), event.getUser(), event.getOrderid());
            
        }
    }
    //cancle user
    private void cancelPayment(String paymentid, String user, String orderid) {
    	
        PaymentCancelCommand command = new PaymentCancelCommand();
        command.setPaymentid(paymentid);
        command.setOrderid(orderid);
        command.setPaymentstatus("CANCELLED");
        
        //user
        command.setUser(user);
        
        commandGateway.send(command);
        
    }
    
    @SagaEventHandler(associationProperty = "orderid")
    private void handle(ShipmentCreatedEvent event) {
    	
        log.info("ShipmentCreatedEvent in Saga for Order Id : {}",
                event.getOrderid());
        
        GetUserCardQuery query = new GetUserCardQuery();
        query.setOrderid(event.getOrderid());
        
        UserCommon user = null;
        
        //catch if turn off user service or user invalid
        try {
            user = queryGateway.query(query,ResponseTypes.instanceOf(UserCommon.class)).join();  
            
            
        } catch (Exception e) {
        	
            log.error(e.getMessage());
            //Start the Compensating transaction
            cancelShipment(event.getShipmentid(), event.getPaymentid(), event.getUser(), event.getOrderid());          
        }
        
        
        try {
        	// if true throw
            //if(true)
            //    throw new Exception();
        	
    		CartRemoveCommand command = new CartRemoveCommand();
    		command.setCart(UUID.randomUUID().toString());
    		command.setOrderid(event.getOrderid());
    		command.setCardstatus("ORDERED");
    		command.setUserid(user.getUserid());
    		
    		//
    		command.setUser(event.getUser());
    		command.setPaymentid(event.getPaymentid());
    		command.setShipmentid(event.getShipmentid());            
            
            commandGateway.sendAndWait(command);  	
        	
        } catch (Exception e) {
        	log.error(e.getMessage());
            
        	cancelShipment(event.getShipmentid(), event.getPaymentid(), event.getUser(), event.getOrderid());
        	
        }      
    }
    
    private void cancelShipment(String shipmentid, String paymentid, String user, String orderid) {
    	
        ShipmentCancelCommand command = new ShipmentCancelCommand();
        command.setShipmentid(shipmentid);
        command.setOrderid(orderid);
        command.setShipmentstatus("CANCELLED");
        
        //
        command.setUser(user);
        command.setPaymentid(paymentid);
        
        commandGateway.send(command);
        
    }
    
    @SagaEventHandler(associationProperty = "orderid")
    private void handle(CartRemovedEvent event) {
        
        log.info("CardRemovedEvent in Saga for User Id : {}",
                event.getUserid()); 
        
        GetOrderQuery getOrderQuery = new GetOrderQuery();
        getOrderQuery.setOrderid(event.getOrderid());
        
        
        OrderCommon ordercommon = null;
        
        //
        try {
        	ordercommon = queryGateway.query(getOrderQuery,ResponseTypes.instanceOf(OrderCommon.class)).join();
           

        } catch (Exception e) {
            log.error(e.getMessage());
            //Start the Compensating transaction
        	cancelCart(event.getCart(), event.getShipmentid(), event.getPaymentid(), 
        			event.getUser(), event.getOrderid());
            
        }
        

        //check ton kho cua tung san pham
        GetProductQuery getproductquery = new GetProductQuery();
        getproductquery.setLineitems(ordercommon.getLineitems());
        
        ProductCommon productcommon = null;
        
        //
        try {
        	productcommon = queryGateway.query(getproductquery,ResponseTypes.instanceOf(ProductCommon.class)).join();
           

        } catch (Exception e) {
            log.error(e.getMessage());
            //Start the Compensating transaction
        	cancelCart(event.getCart(), event.getShipmentid(), event.getPaymentid(), 
        			event.getUser(), event.getOrderid());
        }
        
      
        //log.info("a {} {}", ordercommon.getLineitems().get(0).getProduct(), ordercommon.getLineitems().get(0).getQuantity());
        //log.info("a {} {}", ordercommon.getLineitems().get(1).getProduct(), ordercommon.getLineitems().get(1).getQuantity());

        //log.info("a {} {}", productcommon.getLineitems().get(0).getProduct(), productcommon.getLineitems().get(0).getQuantity());
        //log.info("a {} {}", productcommon.getLineitems().get(1).getProduct(), productcommon.getLineitems().get(1).getQuantity());

        //
    	for (int i = 0; i < productcommon.getLineitems().size(); i++) {
    		
            if (ordercommon.getLineitems().get(i).getQuantity() > productcommon.getLineitems().get(i).getQuantity()) {
            	log.info("Product sold out : {}",
                        productcommon.getLineitems().get(i).getProduct()); 
            	
            	cancelCart(event.getCart(), event.getShipmentid(), event.getPaymentid(), 
            			event.getUser(), event.getOrderid());
            }
    	}
    	
        try {
        	// if true throw
            
            ProductExcludeCommand command = new ProductExcludeCommand();
            command.setProduct(UUID.randomUUID().toString());
            command.setLineitems(ordercommon.getLineitems());
            command.setOrderid(event.getOrderid());
            
            //
            command.setUser(event.getUser());
            command.setPaymentid(event.getPaymentid());
            command.setShipmentid(event.getShipmentid());
            command.setCart(event.getCart());
            
            
            
            commandGateway.sendAndWait(command);	
        	
        } catch (Exception e) {
        	log.error(e.getMessage());
            
        	cancelCart(event.getCart(), event.getShipmentid(), event.getPaymentid(), 
        			event.getUser(), event.getOrderid());
        	
        }   

        
    }
    
    
    //cancle card
    private void cancelCart(String card, String shipmentid, String paymentid, String user, String orderid) {
    	
        CartCancelCommand command = new CartCancelCommand();
        command.setCart(card);
        command.setOrderid(orderid);
        command.setCardstatus("ACTIVE");
        
        //
        command.setUser(user);
        command.setPaymentid(paymentid);
        command.setShipmentid(shipmentid);
        
        commandGateway.send(command);
    }
    
    @SagaEventHandler(associationProperty = "orderid")
    private void handle(ProductExcludedEvent event) {
    	
        log.info("ProductExcludedEvent in Saga for Order Id : {}",
                event.getOrderid());
        
        try {
        	// if true throw
            
            OrderCompleteCommand command = new OrderCompleteCommand();
            command.setOrderid(event.getOrderid());
            command.setOrderstatus("APPROVED");
            
            commandGateway.sendAndWait(command);	
        	
        } catch (Exception e) {
        	log.error(e.getMessage());
            
        	cancelProduct(event.getProduct(), event.getLineitems(), event.getCart(), event.getShipmentid(), 
        			event.getPaymentid(), event.getUser(), event.getOrderid());
        	
        } 
             
    }
    //cancle product
    private void cancelProduct(String product, List<LineItems> lineitems,String card, String shipmentid, String paymentid, String user, String orderid) {
    	
    	ProductCancelCommand command = new ProductCancelCommand();
    	command.setProduct(product);
    	command.setLineitems(lineitems);
    	command.setOrderid(orderid);
    	
    	//
    	command.setUser(user);
    	command.setPaymentid(paymentid);
    	command.setShipmentid(shipmentid);
    	command.setCart(card);
    	
        commandGateway.send(command);
    }
    
    /*@SagaEventHandler(associationProperty = "orderid")
    private void handle(PaymentCreatedEvent event) {
        
        log.info("PaymentProcessedEvent in Saga for Order Id : {}",
                event.getOrderid()); 
        
        try {
            CancelBalanceCommand excludingBalanceCommand = new ExcludingBalanceCommand();
            excludingBalanceCommand.setUser(event.getUser());
            excludingBalanceCommand.setOrderid(event.getOrderid());
            excludingBalanceCommand.setTotal(event.getTotal());
            excludingBalanceCommand.setId(UUID.randomUUID().toString());
            
            commandGateway.sendAndWait(excludingBalanceCommand);
        } catch (Exception e) {
            log.error(e.getMessage());
            // Start the compensating transaction
            //log.error("toi day roi"); 
            cancelPaymentCommand(event.getOrderid());
            
        }
    }
    //cancle payment
    private void cancelPaymentCommand(String orderid) {
        CanclePaymentCommand canclePaymentCommand = new CanclePaymentCommand();
        //canclePaymentCommand.setPaymentid(event.getPaymentid());
        canclePaymentCommand.setOrderid(orderid);
        
        commandGateway.send(canclePaymentCommand);
        
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
            
        }
        
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
        	
        	//cancelOrderCommand(event.getOrderid());
        	
        }
             
    }
    //cancle shipment
    private void cancelShipmentCommand(String orderid) {
        CancelShipmentCommand cancelShipmentCommand = new CancelShipmentCommand();
        cancelShipmentCommand.setOrderid(orderid);
        
        commandGateway.send(cancelShipmentCommand);
        
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
            //cancelOrderCommand(event.getOrderid());
            
        }
        
        EditStockCommand editStockCommand = new EditStockCommand();
        editStockCommand.setLineitems(ordercommon.getLineitems());
        editStockCommand.setOrderid(event.getOrderid());
        
        commandGateway.sendAndWait(editStockCommand);
        
    }*/
    

    
    
    @SagaEventHandler(associationProperty = "orderid")
    @EndSaga
    public void handle(OrderCancelledEvent event) {
        log.info("OrderCancelledEvent in Saga for Order Id : {}",
                event.getOrderid());
    }
    
    @SagaEventHandler(associationProperty = "orderid")
    public void handle(UserCanceledEvent event) {
        log.info("UserCanceledEvent in Saga for Order Id : {}",
                event.getOrderid());
        cancelOrder(event.getOrderid());
    }
    
    @SagaEventHandler(associationProperty = "orderid")
    public void handle(PaymentCanceledEvent event) {
        log.info("PaymentCanceledEvent in Saga for Order Id : {}",
                event.getOrderid());
        
        cancelUser(event.getUser(), event.getOrderid());
    }
    
    @SagaEventHandler(associationProperty = "orderid")
    public void handle(ShipmentCancelledEvent event) {
        
        log.info("ShipmentCancelledEvent in Saga for Order Id : {}",
                event.getOrderid());
        cancelPayment(event.getPaymentid(), event.getUser(), event.getOrderid());
    }
    
    @SagaEventHandler(associationProperty = "orderid")
    public void handle(CartCancelledEvent event) {
        
        log.info("CardCancelledEvent in Saga for Order Id : {}",
                event.getOrderid());
        
        cancelShipment(event.getShipmentid(), event.getPaymentid(), event.getUser(), event.getOrderid());
    }
    @SagaEventHandler(associationProperty = "orderid")
    public void handle(ProductCancelledEvent event) {
        
        log.info("ProductCancelledEvent in Saga for Order Id : {}",
                event.getOrderid());
        
        cancelCart(event.getCart(), event.getShipmentid(), event.getPaymentid(), event.getUser(), event.getOrderid());
    }
    
    
    
    
    
    
    
    
    @SagaEventHandler(associationProperty = "orderid")
    @EndSaga
    public void handle(OrderCompletedEvent event) {
        log.info("OrderCompletedEvent in Saga for Order Id : {}",
                event.getOrderid());
    }
    /*@SagaEventHandler(associationProperty = "orderid")
    public void handle(PaymentCanceledEvent event) {
        log.info("PaymentCancelledEvent in Saga for Order Id : {}",
                event.getOrderid());
        cancelOrder(event.getOrderid());
    }
    
    @SagaEventHandler(associationProperty = "orderid")
    public void handle(BalanceCanceledEvent event) {
        log.info("BalanceCanceledEvent in Saga for User Id : {}",
                event.getUser());
        cancelPaymentCommand(event.getOrderid());
    }
    
    @SagaEventHandler(associationProperty = "orderid")
    public void handle(ShipmentCanceledEvent event) {
        log.info("ShipmentCanceledEvent in Saga for Order Id : {}",
                event.getOrderid());
        //cancelUserBalanceCommand(event.get);
    }*/


}
