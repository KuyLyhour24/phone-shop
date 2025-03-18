package com.lyhour.java.study.phone_shop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lyhour.java.study.phone_shop.dto.SalesDTO;
import com.lyhour.java.study.phone_shop.service.SaleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("sales")
@RequiredArgsConstructor
public class SaleController {
	private final SaleService saleService;
	@PostMapping
	public ResponseEntity<?> saleProduct(@RequestBody SalesDTO saleDTO){
		saleService.sell(saleDTO);
		return ResponseEntity.ok().build();
	}

}
