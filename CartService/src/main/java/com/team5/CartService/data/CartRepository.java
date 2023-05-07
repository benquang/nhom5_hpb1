package com.team5.CartService.data;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CartRepository extends MongoRepository<Cart, String> {
	Cart findByUser(String userid);
	
}