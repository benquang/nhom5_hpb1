package com.team5.ProductService.controller;

import java.util.List;
import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team5.ProductService.data.Product;
import com.team5.ProductService.data.ProductRepository;


@RestController
@RequestMapping("/products")
@CrossOrigin("http://localhost:3000")
public class ProductController {
	
	private ProductRepository productRepository;
	//private CommandGateway commandGateway;
	
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
	
    @GetMapping
	public List<Product> getAllProducts() {
    	
		List<Product> products = productRepository.findAll();
        
		return products;
	}
    
    @GetMapping("/{id}")
	public Product getProduct(@PathVariable("id") String id) {
    	
		Product product = productRepository.findById(id).get();
        
		return product;
	}

}
