package com.lyhour.java.study.phone_shop.service;

import java.math.BigDecimal;

import com.lyhour.java.study.phone_shop.dto.ProductImportDTO;
import com.lyhour.java.study.phone_shop.entity.Product;

public interface ProductService {
	Product create(Product product);
	Product getById(Long id);
	void createImport(ProductImportDTO improtDTO);
	void setPrice(Long id, BigDecimal price);
	//void validateStock(Long productId, Integer numberOfUnit);
	

}
