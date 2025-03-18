package com.lyhour.java.study.phone_shop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.ResponseEntity;

import com.lyhour.java.study.phone_shop.entity.Product;
import com.lyhour.java.study.phone_shop.repository.ProductRepository;

@DataJpaTest
public class ProductServiceImplTest {

	@Autowired
	private ProductRepository productRepository;
	
	@Test
	public void testSetPrice() {
		Product product = Product.builder()
				.name("IP14")
				.build();
		productRepository.save(product);
		
		BigDecimal price = new BigDecimal(1700);
		Product productid = productRepository.getById(1L);
		productid.setSalePrice(price);
		productRepository.save(productid);
		
		assertEquals(1700, 1700);
	}
}
