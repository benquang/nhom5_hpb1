package com.team5.UserService.event;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.team5.CommonService.events.ExcludedBalanceEvent;
import com.team5.CommonService.events.OrderShippedEvent;
import com.team5.UserService.data.User;
import com.team5.UserService.data.UserRepository;

@Component
public class UserEventsHandler {

	private UserRepository userRepository;
	
    public UserEventsHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    
	@EventHandler
	public void on(ExcludedBalanceEvent event) {
        User user = userRepository.findByUserid(event.getUser());
        
        Double currentbalance = user.getBalance();

        user.setBalance(currentbalance-event.getTotal());

        userRepository.save(user);
		
	}
}
