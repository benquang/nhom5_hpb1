package com.team5.PaymentService.event;

import java.util.Date;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import com.team5.CommonService.events.PaymentCanceledEvent;
import com.team5.CommonService.events.PaymentCreatedEvent;
import com.team5.PaymentService.data.Payment;
import com.team5.PaymentService.data.PaymentRepository;

@Component
public class PaymentEventsHandler {
	
	private PaymentRepository paymentRepository;
	
    public PaymentEventsHandler(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }
    
    @EventHandler
    public void on(PaymentCreatedEvent event) {
        Payment payment = new Payment();
        payment.setPaymentid(event.getPaymentid());
        payment.setOrderid(event.getOrderid());
        payment.setPaymentstatus(event.getPaymentstatus());
        payment.setModified_date(new Date());

        paymentRepository.save(payment);
    }
    
    
    @EventHandler
    public void on(PaymentCanceledEvent event) {
        Payment payment
                = paymentRepository.findByOrderid(event.getOrderid());

        payment.setPaymentstatus(event.getPaymentstatus());

        paymentRepository.save(payment);
    }
}
