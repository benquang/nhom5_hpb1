package com.team5.OrderService.controller;

import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team5.OrderService.command.CreateOrderCommand;
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
		
        CreateOrderCommand createOrderCommand = new CreateOrderCommand();
        createOrderCommand.setOrderid(orderid);
        
        //createOrderCommand.setUser(orderRestModel.getUser());
        createOrderCommand.setFullname(orderRestModel.getFullname());
        createOrderCommand.setPhone(orderRestModel.getPhone());
        createOrderCommand.setEmail(orderRestModel.getEmail());
        createOrderCommand.setAddress(orderRestModel.getAddress());
        createOrderCommand.setOrdernote(orderRestModel.getOrdernote());
        createOrderCommand.setLineitems(orderRestModel.getLineitems());
        createOrderCommand.setTotal(100.5);
        createOrderCommand.setOrderstatus("CREATED");
        
        commandGateway.sendAndWait(createOrderCommand);

		return "Order created";
	}

}
