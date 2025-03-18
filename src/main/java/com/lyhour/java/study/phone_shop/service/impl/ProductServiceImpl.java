package com.lyhour.java.study.phone_shop.service.impl;

import java.math.BigDecimal;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.lyhour.java.study.phone_shop.dto.ProductImportDTO;
import com.lyhour.java.study.phone_shop.entity.Product;
import com.lyhour.java.study.phone_shop.entity.ProductImportHistory;
import com.lyhour.java.study.phone_shop.exception.ApiException;
import com.lyhour.java.study.phone_shop.exception.ResourceNotFoundException;
import com.lyhour.java.study.phone_shop.mapper.ProductMapper;
import com.lyhour.java.study.phone_shop.repository.ProductImportRepository;
import com.lyhour.java.study.phone_shop.repository.ProductRepository;
import com.lyhour.java.study.phone_shop.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

	private final ProductRepository productRepository;
	private final ProductImportRepository importRepository;
	private final ProductMapper productMapper;
	@Override
	public Product create(Product product) {
		String name ="%s %s"
				.formatted(product.getModel().getName(),product.getColor().getName());
		product.setName(name);
		return productRepository.save(product);
	}
	@Override
	public Product getById(Long id) {
		return productRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Product", id));
	}
	@Override
	public void createImport(ProductImportDTO importDTO) {
		Product product = getById(importDTO.getProductId());
		Integer availableUnit =0;
		if(product.getAvailableUnit() !=null) {
			availableUnit = product.getAvailableUnit();	
		}
		product.setAvailableUnit(availableUnit + importDTO.getImportUnit());
		productRepository.save(product);
		
		ProductImportHistory importHistory = productMapper.toImportHistory(importDTO, product);
		importRepository.save(importHistory);
		
	
		
	}
	@Override
	public void setPrice(Long id, BigDecimal price) {
		Product product = getById(id);
		product.setSalePrice(price);
		productRepository.save(product);
		
	}
		
	
	

}
