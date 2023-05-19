package com.team5.UserService.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team5.UserService.data.User;
import com.team5.UserService.data.UserRepository;
import com.team5.UserService.model.UserRestModel;

@RestController
@RequestMapping("/users")
@CrossOrigin("http://localhost:3000")
public class UserController {
	private static Logger log = LoggerFactory.getLogger(UserController.class);
	
	private UserRepository userRepository;
	
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@PostMapping
	public User getUser(@RequestBody UserRestModel userRestModel) {
		
		User user = userRepository.findByUserid(userRestModel.getUserid());
		
		//log.info("User : {}, {}",
		//		userRestModel.getUserid(), userRestModel.getPassword()); 
		
		if(user == null) {
			return null;
		}
		
		if(user.getPassword().equals(userRestModel.getPassword())) {
			return user;
		}
		
		return null;
	}
	
	@PostMapping("/info")
	public User getUserInfo(@RequestBody UserRestModel userRestModel) {
		
		User user = userRepository.findByUserid(userRestModel.getUserid());
		
		if(user == null) {
			return null;
		}
		
		return user;
	}
	
	@PostMapping("/register")
	public User register(@RequestBody UserRestModel userRestModel) {
		
		User user = new User();
		user.setUserid(userRestModel.getUserid());
		user.setPassword(userRestModel.getPassword());
		user.setFullname(userRestModel.getFullname());
		user.setCardnumber(userRestModel.getCardnumber());
		
		user.setBalance(10000.0);
		user.setValidmonth(9);
		
		user.setLastorder("none");
		user.setLastpay(1.0);
		
		userRepository.save(user);
		
		return user;
	}
	
	

}
