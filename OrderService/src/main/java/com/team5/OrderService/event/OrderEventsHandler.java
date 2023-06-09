package com.team5.OrderService.event;

import java.util.Date;

import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.team5.CommonService.events.OrderCancelledEvent;
import com.team5.CommonService.events.OrderCompletedEvent;
import com.team5.OrderService.data.Order;
import com.team5.OrderService.data.OrderRepository;

@Component
public class OrderEventsHandler {

	private OrderRepository orderRepository;
	
    public OrderEventsHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

	@EventHandler
	public void on(OrderCreatedEvent event) {
		Order order = new Order();
		BeanUtils.copyProperties(event, order);
		
		order.setOrderdate(new Date());
		
		orderRepository.save(order);
		
	}
	
    @EventHandler
    public void on(OrderCompletedEvent event) {
        Order order = orderRepository.findById(event.getOrderid()).get();

        order.setOrderstatus(event.getOrderstatus());

        orderRepository.save(order);
    }
    
    @EventHandler
    public void on(OrderCancelledEvent event) {
        Order order = orderRepository.findById(event.getOrderid()).get();

        order.setOrderstatus(event.getOrderstatus());

        orderRepository.save(order);
    }
    
}
