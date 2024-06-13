package com.lyhour.java.study.phone_shop.helper;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.lyhour.java.study.phone_shop.entity.Product;
import com.lyhour.java.study.phone_shop.entity.ProductImportHistory;
import com.lyhour.java.study.phone_shop.entity.Sale;
import com.lyhour.java.study.phone_shop.entity.SaleDetail;

public class ReportServiceTestHelper {
	
		private static Product product1 = Product.builder()
				.id(1L)
				.name("iphone 14")
				.build();
		private static Product product2 = Product.builder()
				.id(2L)
				.name("iphone 13")
				.build();
		private static Product product3 = Product.builder()
				.id(3L)
				.name("iphone 12")
				.build();
	public static List<Product> getProduct(){
		List<Product> list = List.of(product1,product2);
		return list;
	}
	
	private static Sale sale = Sale.builder()
			.id(1L)
			.soldDate(LocalDateTime.of(2024, 06, 02, 02, 30, 40))
			.build();
	private static Sale sale1 = Sale.builder()
			.id(2L)
			.soldDate(LocalDateTime.of(2024, 06, 03, 02, 30, 40))
			.build();
	
	public static List<SaleDetail> getSaleDetail(){
		SaleDetail saleDetial = SaleDetail.builder()
				.id(1L)
				.sale(sale)
				.product(product1)
				.amount(BigDecimal.valueOf(1200))
				.unit(10)
				.build();
		SaleDetail saleDetial1 = SaleDetail.builder()
				.id(1L)
				.sale(sale1)
				.product(product2)
				.amount(BigDecimal.valueOf(1200))
				.unit(20)
				.build();
		List<SaleDetail> list = List.of(saleDetial,saleDetial1);
		return list;
	}

	public static List<ProductImportHistory> getImportHistories(){
		ProductImportHistory importHistories = ProductImportHistory.builder()
				.id(1L)
				.pricePerUnit(BigDecimal.valueOf(1200))
				.importUnit(10)
				.importDate(LocalDateTime.of(2024, 05, 31, 10, 37,40))
				.product(product1)
				.build();
		ProductImportHistory importHistories1 = ProductImportHistory.builder()
				.id(2L)
				.pricePerUnit(BigDecimal.valueOf(1200))
				.importUnit(20)
				.importDate(LocalDateTime.of(2024, 06, 30, 10, 37,40))
				.product(product2)
				.build();
		ProductImportHistory importHistories2 = ProductImportHistory.builder()
				.id(3L)
				.pricePerUnit(BigDecimal.valueOf(1200))
				.importUnit(10)
				.importDate(LocalDateTime.of(2024, 05, 31, 10, 37,40))
				.product(product1)
				.build();
		
		List<ProductImportHistory> list = List.of(importHistories,importHistories1,importHistories2);
		return list;
	}
}
