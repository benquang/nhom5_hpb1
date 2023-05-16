package com.team5.ShipmentService.event;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.team5.CommonService.events.ShipmentCreatedEvent;
import com.team5.CommonService.events.ShipmentCancelledEvent;
import com.team5.ShipmentService.data.Shipment;
import com.team5.ShipmentService.data.ShipmentRepository;

@Component
public class ShipmentEventsHandler {

	private ShipmentRepository shipmentRepository;
	
    public ShipmentEventsHandler(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }
    
    
	@EventHandler
	public void on(ShipmentCreatedEvent event) {
		Shipment shipment = new Shipment();
		
		shipment.setShipmentid(event.getShipmentid());
		shipment.setOrderid(event.getOrderid());
		shipment.setShipmentstatus(event.getShipmentstatus());
		//BeanUtils.copyProperties(event, shipment);
		
		shipmentRepository.save(shipment);
	}
	
	@EventHandler
	public void on(ShipmentCancelledEvent event) {
		Shipment shipment = shipmentRepository.findByOrderid(event.getOrderid());
		
		shipment.setShipmentstatus(event.getShipmentstatus());
		
		shipmentRepository.save(shipment);
	}
}
