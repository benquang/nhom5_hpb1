package com.team5.ProductService.projection;

import java.util.ArrayList;
import java.util.List;

import org.axonframework.queryhandling.QueryHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.team5.CommonService.model.LineItems;
import com.team5.CommonService.model.ProductCommon;
import com.team5.CommonService.queries.GetProductQuery;
import com.team5.ProductService.aggregate.ProductAggregate;
import com.team5.ProductService.data.Product;
import com.team5.ProductService.data.ProductRepository;
@Component
public class ProductProjection {
	
	private static Logger log = LoggerFactory.getLogger(ProductProjection.class);
	
	private ProductRepository productRepository;
	
    public ProductProjection(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

	@QueryHandler
	public ProductCommon getProductCommon(GetProductQuery query) {
		//ideally get from db
		ProductCommon productCommon = new ProductCommon();
		
		List<LineItems> lineitems = new ArrayList<>();
	
		for (int i = 0; i < query.getLineitems().size(); i++) {
			
			Product product1 = productRepository.findById(query.getLineitems().get(i).getProduct()).get();
	        
	        LineItems lineitem = new LineItems();
	        lineitem.setProduct(product1.getId());
	        lineitem.setQuantity(product1.getStock());
	        lineitem.setUnitprice(product1.getPrice());
	       
	        //
	        lineitems.add(lineitem);
	            
			
		}

		productCommon.setLineitems(lineitems);
		return productCommon;
	}
}
