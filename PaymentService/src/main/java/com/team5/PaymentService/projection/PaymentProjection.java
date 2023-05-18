package com.team5.PaymentService.projection;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import com.team5.CommonService.model.PaymentCommon;
import com.team5.CommonService.queries.GetPaymentQuery;
import com.team5.PaymentService.data.Payment;
import com.team5.PaymentService.data.PaymentRepository;

@Component
public class PaymentProjection {
	
	private PaymentRepository paymentRepository;
	
	public PaymentProjection(PaymentRepository paymentRepository) {
		this.paymentRepository = paymentRepository;
	}

	@QueryHandler
	public PaymentCommon getPaymentCommon(GetPaymentQuery query) {
		
		//Payment payment = paymentRepository.findByOrderid(query.getOrderid());
		Payment payment = paymentRepository.findByOrderid(query.getOrderid());
		
		PaymentCommon paymentcommon = new PaymentCommon();
		paymentcommon.setPaymentid(payment.getPaymentid());
		paymentcommon.setPaymentstatus(payment.getPaymentstatus());
		
		return paymentcommon;
	}
}
