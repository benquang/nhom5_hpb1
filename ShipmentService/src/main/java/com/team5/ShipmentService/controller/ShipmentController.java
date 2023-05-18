package com.team5.ShipmentService.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team5.ShipmentService.data.Shipment;
import com.team5.ShipmentService.data.ShipmentRepository;
import com.team5.ShipmentService.model.ShipmentRestModel;

@RestController
@RequestMapping("/shipments")
@CrossOrigin("http://localhost:3000")
public class ShipmentController {

	private ShipmentRepository shipmentRepository;
	
	public ShipmentController(ShipmentRepository shipmentRepository) {
		this.shipmentRepository = shipmentRepository;
	}
	
	@PostMapping
	public Shipment getShipment(@RequestBody ShipmentRestModel shipmentRestModel) {
		
		Shipment shipment = shipmentRepository.findByOrderid(shipmentRestModel.getOrderid());
		
		return shipment;
	}
	
}
