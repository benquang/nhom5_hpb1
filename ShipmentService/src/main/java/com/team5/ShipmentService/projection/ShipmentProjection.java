package com.team5.ShipmentService.projection;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import com.team5.CommonService.model.ShipmentCommon;
import com.team5.CommonService.queries.GetShipmentQuery;
import com.team5.ShipmentService.data.Shipment;
import com.team5.ShipmentService.data.ShipmentRepository;

@Component
public class ShipmentProjection {
	
	private ShipmentRepository shipmentRepository;

	public ShipmentProjection(ShipmentRepository shipmentRepository) {
		this.shipmentRepository = shipmentRepository;
	}
	
	@QueryHandler
	public ShipmentCommon getShipmentCommon(GetShipmentQuery query) {
		
		ShipmentCommon shipmentCommon = new ShipmentCommon();
		
		Shipment shipment = shipmentRepository.findByOrderid(query.getOrderid());
		
		shipmentCommon.setShipmentid(shipment.getShipmentid());
		shipmentCommon.setShipmentstatus(shipment.getShipmentstatus());
		
		return shipmentCommon;
	}
}
