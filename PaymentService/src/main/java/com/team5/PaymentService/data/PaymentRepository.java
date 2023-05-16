package com.team5.PaymentService.data;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface PaymentRepository extends MongoRepository<Payment, String> {
	Payment findByOrderid(String orderid);
}