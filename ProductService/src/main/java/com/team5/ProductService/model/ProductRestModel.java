package com.team5.ProductService.model;

import java.util.List;

import com.team5.ProductService.data.Product;

public class ProductRestModel {
	
	private String product;
	private List<Product> products;

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	
	
	

}
