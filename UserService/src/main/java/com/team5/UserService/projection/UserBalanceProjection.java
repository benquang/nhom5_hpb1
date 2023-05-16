package com.team5.UserService.projection;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.team5.CommonService.model.UserCommon;
import com.team5.CommonService.queries.GetUserBalanceQuery;
import com.team5.CommonService.queries.GetUserCardQuery;
import com.team5.UserService.data.User;
import com.team5.UserService.data.UserRepository;

@Component
public class UserBalanceProjection {
	private UserRepository userRepository;
	
    public UserBalanceProjection(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

	@QueryHandler
	public UserCommon getUserBalance(GetUserBalanceQuery query) {
		//ideally get from db
        User user = userRepository.findByUserid(query.getUser());   
         
        UserCommon user1 = new UserCommon();
        
        BeanUtils.copyProperties(user, user1);
        
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
		
		return user1;
	}
	
	@QueryHandler
	public UserCommon getUserCard(GetUserCardQuery query) {
		//ideally get from db
        User user = userRepository.findByLastorder(query.getOrderid());   
         
        UserCommon user1 = new UserCommon();
        
        BeanUtils.copyProperties(user, user1);
        
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
		
		return user1;
	}
}
