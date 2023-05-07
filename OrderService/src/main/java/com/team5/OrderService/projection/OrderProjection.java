package com.team5.OrderService.projection;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import com.team5.CommonService.model.OrderCommon;
import com.team5.CommonService.queries.GetOrderQuery;
import com.team5.OrderService.data.Order;
import com.team5.OrderService.data.OrderRepository;

@Component
public class OrderProjection {
	private OrderRepository orderRepository;
	
    public OrderProjection(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

	@QueryHandler
	public OrderCommon getOrderCommon(GetOrderQuery query) {
		//ideally get from db
        Order order = orderRepository.findById(query.getOrderid()).get();   
         
        OrderCommon orderCommon = new OrderCommon();
        orderCommon.setOrderid(order.getOrderid());
        orderCommon.setUser(order.getUser());
        orderCommon.setLineitems(order.getLineitems());
        
		/*CardDetails cartDetails = new CardDetails();
		cartDetails.setCartnumber("123456789");
		cartDetails.setValiduntilmonth(5);
		cartDetails.setBalance(150.5);
		
		User user = new User();
		user.setUserid(query.getUser());
		user.setPassword("123");
		user.setFirstname("Bao");
		user.setLastname("Thy");
		user.setCartdetails(cartDetails);*/
		
		return orderCommon;
	}
}
