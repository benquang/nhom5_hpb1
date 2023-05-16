package com.team5.ProductService.events;

import java.util.Date;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import com.team5.CommonService.events.ProductExcludedEvent;
import com.team5.CommonService.events.PaymentCanceledEvent;
import com.team5.CommonService.events.PaymentCreatedEvent;
import com.team5.CommonService.events.ProductCancelledEvent;
import com.team5.ProductService.data.Product;
import com.team5.ProductService.data.ProductRepository;

@Component
public class ProductEventsHandler {
	
	private ProductRepository productRepository;
	
    public ProductEventsHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    @EventHandler
    public void on(ProductExcludedEvent event) {
    	
    	for (int i = 0; i < event.getLineitems().size(); i++) {
    		
            Product product = productRepository.findById(event.getLineitems().get(i).getProduct()).get();

    		int currentstock = product.getStock();
    		int quantity = event.getLineitems().get(i).getQuantity();
    		
            product.setStock(currentstock - quantity);

            productRepository.save(product);
    	}   	   	
    }
    
    @EventHandler
    public void on(ProductCancelledEvent event) {
    	
    	for (int i = 0; i < event.getLineitems().size(); i++) {
    		
            Product product = productRepository.findById(event.getLineitems().get(i).getProduct()).get();

    		int currentstock = product.getStock();
    		int quantity = event.getLineitems().get(i).getQuantity();
    		
            product.setStock(currentstock + quantity);

            productRepository.save(product);
    	}   	   	
    }
    
}
