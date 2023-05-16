package com.team5.OrderService.controller;

import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team5.OrderService.command.OrderCreateCommand;
import com.team5.OrderService.model.OrderRestModel;

@RestController
@RequestMapping("/orders")
public class OrderCommandController {
	
	private CommandGateway commandGateway;
	
    public OrderCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
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

}
