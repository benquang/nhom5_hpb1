package com.team5.ProductService.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team5.ProductService.data.Product;
import com.team5.ProductService.data.ProductRepository;
import com.team5.ProductService.model.ProductOrdersModel;
import com.team5.ProductService.model.ProductRestModel;


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
    
    //get list product  
    @PostMapping
	public List<Product> getProducts(@RequestBody ProductRestModel productRestModel) {
    	
    	List<Product> products = new ArrayList<>();
    	
    	for (int i = 0; i < productRestModel.getProducts().size(); i++) {
    		Product product = productRepository.findById(productRestModel.getProducts().get(i).getId()).get();
    		
    		products.add(product);
    	}
        
		return products;
	}
    
    @PostMapping("/orders")
	public List<ProductOrdersModel> getProductsOrders(@RequestBody List<ProductRestModel> productRestModels) {
    	
    	//List<ProductRestModel> newlist = new ArrayList<>();
    	
    	List<ProductOrdersModel> aaa =  new ArrayList<>();
    	
    	
    	for (int i = 0; i < productRestModels.size(); i++) {
    		
    		ProductOrdersModel temp = new ProductOrdersModel();
    		
    		List<Product> products = new ArrayList<>();
    		
    		for(int k = 0; k < productRestModels.get(i).getProducts().size(); k++) {
        		Product product = productRepository.findById(productRestModels.get(i).getProducts().get(k).getId()).get();
        		
        		products.add(product);

    		}
    		temp.setLineitems(products);
    		
    		aaa.add(temp);
    	}
        
		return aaa;
	}

}
