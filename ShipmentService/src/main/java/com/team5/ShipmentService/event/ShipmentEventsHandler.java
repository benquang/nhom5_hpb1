package com.team5.ShipmentService.event;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.team5.CommonService.events.OrderShippedEvent;
import com.team5.ShipmentService.data.Shipment;
import com.team5.ShipmentService.data.ShipmentRepository;

@Component
public class ShipmentEventsHandler {

	private ShipmentRepository shipmentRepository;
	
    public ShipmentEventsHandler(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }
    
    
	@EventHandler
	public void on(OrderShippedEvent event) {
		Shipment shipment = new Shipment();
		
		BeanUtils.copyProperties(event, shipment);
		
		shipmentRepository.save(shipment);
	}
}
