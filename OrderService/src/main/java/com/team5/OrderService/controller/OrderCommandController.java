package com.team5.OrderService.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team5.CommonService.model.LineItems;
import com.team5.CommonService.model.PaymentCommon;
import com.team5.CommonService.model.ProductCommon;
import com.team5.CommonService.model.ProductCommon1;
import com.team5.CommonService.model.ShipmentCommon;
import com.team5.CommonService.queries.GetPaymentQuery;
import com.team5.CommonService.queries.GetProductQuery;
import com.team5.CommonService.queries.GetShipmentQuery;
import com.team5.OrderService.command.OrderCreateCommand;
import com.team5.OrderService.data.Order;
import com.team5.OrderService.data.OrderFullInfo;
import com.team5.OrderService.data.OrderRepository;
import com.team5.OrderService.model.OrderRestModel;

@RestController
@RequestMapping("/orders")
@CrossOrigin("http://localhost:3000")
public class OrderCommandController {
	
	private CommandGateway commandGateway;
	
	private QueryGateway queryGateway;
	
	private OrderRepository orderRepository;
	
    public OrderCommandController(CommandGateway commandGateway, QueryGateway queryGateway, 
    		                      OrderRepository orderRepository) 
    {
    	this.queryGateway = queryGateway;
        this.commandGateway = commandGateway;
        this.orderRepository = orderRepository;
    }

	
    @PostMapping
	public String createOrder(@RequestBody OrderRestModel orderRestModel) {
		String orderid = UUID.randomUUID().toString();
		
        OrderCreateCommand command = new OrderCreateCommand();
        command.setOrderid(orderid);
        
        command.setUser(orderRestModel.getUser());
        command.setFullname(orderRestModel.getFullname());
        command.setPhone(orderRestModel.getPhone());
        command.setEmail(orderRestModel.getEmail());
        command.setAddress(orderRestModel.getAddress());
        command.setOrdernote(orderRestModel.getOrdernote());
        command.setLineitems(orderRestModel.getLineitems());
        //
        Double total = 0.0;
        for (int i = 0; i < orderRestModel.getLineitems().size(); i++) {
        	total += orderRestModel.getLineitems().get(i).getUnitprice() * orderRestModel.getLineitems().get(i).getQuantity();
        }
        command.setTotal(total);
        command.setOrderstatus("CREATED");
        
        commandGateway.sendAndWait(command);

		return "Order Created";
	}
    
    @PostMapping("/userorders")
	public List<OrderFullInfo> getUserOrders(@RequestBody OrderRestModel orderRestModel) {
		
    	List<OrderFullInfo> ordersfullinfo = new ArrayList<>();
    			
		List<Order> orders = orderRepository.findByUser(orderRestModel.getUser());
		
		for (int i = 0; i < orders.size(); i++) {
			
			OrderFullInfo temp = new OrderFullInfo();
			temp.setOrderid(orders.get(i).getOrderid());
			temp.setUser(orders.get(i).getUser());
			temp.setFullname(orders.get(i).getFullname());
			temp.setPhone(orders.get(i).getPhone());
			temp.setEmail(orders.get(i).getEmail());
			temp.setAddress(orders.get(i).getAddress());
			temp.setOrdernote(orders.get(i).getOrdernote());
			temp.setTotal(orders.get(i).getTotal());
			temp.setOrderstatus(orders.get(i).getOrderstatus());
			temp.setOrderdate(orders.get(i).getOrderdate());
			
			//products
			GetProductQuery getproductquery = new GetProductQuery();
	        getproductquery.setLineitems(orders.get(i).getLineitems());
	        
	        ProductCommon1 productcommon = null;
	        productcommon = queryGateway.query(getproductquery,ResponseTypes.instanceOf(ProductCommon1.class)).join();
			
		    temp.setLineitems(productcommon.getLineitems());

	        //payment status
		    GetPaymentQuery getPaymentQuery = new GetPaymentQuery();
		    getPaymentQuery.setOrderid(orders.get(i).getOrderid());
		    
		    PaymentCommon paymentcommon = null;
		    paymentcommon = queryGateway.query(getPaymentQuery, ResponseTypes.instanceOf(PaymentCommon.class)).join();
		    
		    temp.setPaymentstatus(paymentcommon.getPaymentstatus());
	        

			
			//shipment status
			GetShipmentQuery getShipmentQuery = new GetShipmentQuery();
			getShipmentQuery.setOrderid(orders.get(i).getOrderid());
			
			ShipmentCommon shipmentCommon = null;
			shipmentCommon = queryGateway.query(getShipmentQuery, ResponseTypes.instanceOf(ShipmentCommon.class)).join();
			
			temp.setShipmentstatus(shipmentCommon.getShipmentstatus());
			
			//add
			ordersfullinfo.add(temp);	
		}
		

		
		return ordersfullinfo;
		
    }
    

}
