package com.team5.ShipmentService.data;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ShipmentRepository extends MongoRepository<Shipment, String> {
	Shipment findByOrderid(String orderid);
}