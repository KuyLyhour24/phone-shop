package com.lyhour.java.study.phone_shop.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lyhour.java.study.phone_shop.dto.ProductDTO;
import com.lyhour.java.study.phone_shop.dto.ProductImportDTO;
import com.lyhour.java.study.phone_shop.dto.SetSalePriceDTO;
import com.lyhour.java.study.phone_shop.entity.Product;
import com.lyhour.java.study.phone_shop.mapper.ProductMapper;
import com.lyhour.java.study.phone_shop.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("products")
public class ProductController {

	private final ProductService productService;
	private final ProductMapper productMapper;

	@PostMapping
	public ResponseEntity<?> create(@RequestBody ProductDTO productDTO) {
		Product product = productMapper.toProduct(productDTO);
		product = productService.create(product);
		return ResponseEntity.ok(product);
	}

	@PostMapping("import")
	public ResponseEntity<?> importProduct(@RequestBody @Valid ProductImportDTO importProductDTO) {
		productService.createImport(importProductDTO);

		return ResponseEntity.ok().build();
	} 
	
	@PostMapping("{id}/setSalePrice")
	public ResponseEntity<?> setSalePrice(@PathVariable Long id, @RequestBody SetSalePriceDTO saleDTO){
		productService.setPrice(id, saleDTO.getPrice());
		return ResponseEntity.ok().build();
	}
	@PostMapping("upload")
	public ResponseEntity<?> uploadProduct(@RequestParam("file") MultipartFile file){
		Map<Integer, String> errorMap = productService.productImport(file);
		return ResponseEntity.ok(errorMap);
	}
}
