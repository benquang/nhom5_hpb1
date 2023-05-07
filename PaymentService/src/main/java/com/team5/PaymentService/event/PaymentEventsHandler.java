package com.team5.PaymentService.event;

import java.util.Date;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import com.team5.CommonService.events.PaymentCanceledEvent;
import com.team5.CommonService.events.PaymentProcessedEvent;
import com.team5.PaymentService.data.Payment;
import com.team5.PaymentService.data.PaymentRepository;

@Component
public class PaymentEventsHandler {
	
	private PaymentRepository paymentRepository;
	
    public PaymentEventsHandler(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }
    
    @EventHandler
    public void on(PaymentProcessedEvent event) {
        Payment payment = new Payment();
        payment.setPaymentid(event.getPaymentid());
        payment.setOrderid(event.getOrderid());
        payment.setTotal(event.getTotal());
        payment.setPaymentstatus("COMPLETED");
        payment.setModified_date(new Date());

        paymentRepository.save(payment);
    }
    
    
    @EventHandler
    public void on(PaymentCanceledEvent event) {
        Payment payment
                = paymentRepository.findById(event.getPaymentid()).get();

        payment.setPaymentstatus(event.getPaymentstatus());

        paymentRepository.save(payment);
    }
}
