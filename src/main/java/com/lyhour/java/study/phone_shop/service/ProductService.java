package com.lyhour.java.study.phone_shop.service;

import com.lyhour.java.study.phone_shop.dto.ProductImportDTO;
import com.lyhour.java.study.phone_shop.entity.Product;
import com.lyhour.java.study.phone_shop.entity.ProductImportHistory;

public interface ProductService {
	Product create(Product product);
	Product getById(Long id);
	void createImport(ProductImportDTO improtDTO);

}
