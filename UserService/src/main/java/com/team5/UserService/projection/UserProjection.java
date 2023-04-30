package com.team5.UserService.projection;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import com.team5.CommonService.model.CardDetails;
import com.team5.CommonService.model.User;
import com.team5.CommonService.queries.GetUserPaymentDetailsQuery;

@Component
public class UserProjection {

	@QueryHandler
	public User getUserPaymentDetails(GetUserPaymentDetailsQuery query) {
		//ideally get from db
		CardDetails cartDetails = new CardDetails();
		cartDetails.setCartnumber("123456789");
		cartDetails.setValiduntilmonth(5);
		cartDetails.setBalance(150.5);
		
		User user = new User();
		user.setUserid(query.getUser());
		user.setPassword("123");
		user.setFirstname("Bao");
		user.setLastname("Thy");
		user.setCartdetails(cartDetails);
		
		return user;
	}
}
