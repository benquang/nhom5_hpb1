package com.team5.UserService.event;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import com.team5.CommonService.events.UserCanceledEvent;
import com.team5.CommonService.events.BalanceExcludedEvent;
import com.team5.UserService.data.User;
import com.team5.UserService.data.UserRepository;

@Component
public class UserEventsHandler {
	
	private UserRepository userRepository;
	
    public UserEventsHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    
	@EventHandler
	public void on(BalanceExcludedEvent event) {
		
		
		//
        User user = userRepository.findByUserid(event.getUserid());
        user.setLastorder(event.getOrderid());
        user.setLastpay(event.getTotal());
        
        Double currentbalance = user.getBalance();

        user.setBalance(currentbalance - event.getTotal());
        
        userRepository.save(user);
		
	}
	
	@EventHandler
	public void on(UserCanceledEvent event) {
		
        User user = userRepository.findByLastorder(event.getOrderid());
        
        Double currentbalance = user.getBalance();
        Double lastpay = user.getLastpay();
        
        user.setBalance(currentbalance + lastpay);
        
        userRepository.save(user);	
	}
}
