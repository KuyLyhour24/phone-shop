package com.lyhour.java.study.phone_shop.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.lyhour.java.study.phone_shop.dto.SalesDTO;
import com.lyhour.java.study.phone_shop.entity.Product;
import com.lyhour.java.study.phone_shop.entity.Sale;
import com.lyhour.java.study.phone_shop.entity.SaleDetail;
import com.lyhour.java.study.phone_shop.exception.ApiException;
import com.lyhour.java.study.phone_shop.repository.ProductRepository;
import com.lyhour.java.study.phone_shop.repository.SaleDetailRepository;
import com.lyhour.java.study.phone_shop.repository.SaleRepository;
import com.lyhour.java.study.phone_shop.service.ProductService;
import com.lyhour.java.study.phone_shop.service.SaleService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SaleServiceImpl implements SaleService {
	private final ProductService productService;
	private final ProductRepository productRepository;
	private final SaleRepository saleRepository;
	private final SaleDetailRepository detailRepository;
	
	Sale sale= new Sale();

	@Override
	public void sell(SalesDTO salesDTO) {
		validation(salesDTO);
		saveSale(salesDTO);
		saveSaleDetail(salesDTO);
	}

	private void saveSale(SalesDTO salesDTO) {
		sale.setSoldDate(salesDTO.getSoldDate());
		saleRepository.save(sale);
	}
	
	private void saveSaleDetail(SalesDTO salesDTO) {
		salesDTO.getProducts().forEach(ps -> {
			Product product = productService.getById(ps.getProductId());
			SaleDetail saleDetail = new SaleDetail();
			saleDetail.setAmount(product.getSalePrice());
			saleDetail.setUnit(ps.getNumberOfUnit());
			saleDetail.setSale(sale);
			saleDetail.setProduct(product);
			detailRepository.save(saleDetail);
			
			Integer availabelUnit =product.getAvailableUnit()- ps.getNumberOfUnit();
			product.setAvailableUnit(availabelUnit);
			productRepository.save(product);
		});
	}

	private void validation(SalesDTO salesDTO) {
		// Validate Product
		salesDTO.getProducts().forEach(ps -> {
			Product product = productService.getById(ps.getProductId());
			if (product.getAvailableUnit() < ps.getNumberOfUnit()) {
				throw new ApiException(HttpStatus.BAD_REQUEST,
						"Product [%s] is not enough product in stock".formatted(product.getName()));
			}
		});
	}
}
//Validate Product
//List<Long> productIds = salesDTO.getProducts().stream().map(ProductSoldDTO::getProductId).toList();
//productIds.forEach(productService::getById);
//
//Map<Long, Product> productMap = productRepository.findAllById(productIds).stream()
//		.collect(Collectors.toMap(Product::getId, Function.identity()));
//
//salesDTO.getProducts().stream().map(ProductSoldDTO::getProductId).forEach(productService::getById);
// Validate Stock

//salesDTO.getProducts().forEach(ps -> {
//	Product product = productMap.get(ps.getProductId());
//	if (product.getAvailableUnit() < ps.getNumberOfUnit()) {
//		throw new ApiException(HttpStatus.BAD_REQUEST,
//				"Product [%s] is not enouge product in stock".formatted(product.getName()));
//	}
//});
