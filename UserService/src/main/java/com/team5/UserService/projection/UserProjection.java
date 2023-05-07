package com.team5.UserService.projection;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.team5.CommonService.model.User1;
import com.team5.CommonService.queries.GetUserPaymentDetailsQuery;
import com.team5.UserService.data.User;
import com.team5.UserService.data.UserRepository;

@Component
public class UserProjection {
	private UserRepository userRepository;
	
    public UserProjection(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

	@QueryHandler
	public User1 getUserPaymentDetails(GetUserPaymentDetailsQuery query) {
		//ideally get from db
        User user = userRepository.findByUserid(query.getUser());   
         
        User1 user1 = new User1();
        
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
