package com.lyhour.java.study.phone_shop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.lyhour.java.study.phone_shop.dto.ExpenseReportDTO;
import com.lyhour.java.study.phone_shop.dto.ProductReportDTO;
import com.lyhour.java.study.phone_shop.entity.Product;
import com.lyhour.java.study.phone_shop.entity.ProductImportHistory;
import com.lyhour.java.study.phone_shop.entity.SaleDetail;
import com.lyhour.java.study.phone_shop.helper.ReportServiceTestHelper;
import com.lyhour.java.study.phone_shop.repository.ProductImportRepository;
import com.lyhour.java.study.phone_shop.repository.ProductRepository;
import com.lyhour.java.study.phone_shop.repository.SaleDetailRepository;
import com.lyhour.java.study.phone_shop.service.impl.ReportProductServiceImpl;
import com.lyhour.java.study.phone_shop.spec.ProductImportHistoriesSpec;
import com.lyhour.java.study.phone_shop.spec.SaleDetailsSpec;

@ExtendWith(MockitoExtension.class)
public class ReportServiceTest {
	@Mock
	private SaleDetailRepository saleDetailRepository;
	@Mock
	private ProductRepository productRepository;
	@Mock
	private ProductImportRepository importRepository;

	private ReportService reportService;

	@BeforeEach
	public void setup() {
		reportService = new ReportProductServiceImpl(saleDetailRepository, productRepository, importRepository);
	}

	@Test
	public void testGetExpenseReports() {
		// Given
		List<ProductImportHistory> importHistories = ReportServiceTestHelper.getImportHistories();
		List<Product> productIds = ReportServiceTestHelper.getProduct();
		// When
		when(importRepository.findAll(Mockito.any(ProductImportHistoriesSpec.class))).thenReturn(importHistories);
		when(productRepository.findAllById(anyList())).thenReturn(productIds);
		List<ExpenseReportDTO> expenseReports = reportService.getExpenseReports(
				LocalDateTime.of(2024, 05, 31, 10, 37, 40), LocalDateTime.of(2024, 06, 30, 10, 37, 40));

		assertEquals(2, expenseReports.size());
		ExpenseReportDTO expense1 = expenseReports.get(0);
		assertEquals(1, expense1.getProductId());
		assertEquals("iphone 14", expense1.getProductName());
		assertEquals(20, expense1.getTotalUnit());
		assertEquals(24000d, expense1.getTotalAmount().doubleValue());

	}

	@Test
	public void testGetProductReports() {
		List<SaleDetail> saleDetail = ReportServiceTestHelper.getSaleDetail();
		List<Product> productIds = ReportServiceTestHelper.getProduct();
		when(saleDetailRepository.findAll(Mockito.any(SaleDetailsSpec.class))).thenReturn(saleDetail);
		when(productRepository.findAllById(anyList())).thenReturn(productIds);
		
		List<ProductReportDTO> productReports = reportService.getProductReports(LocalDateTime.now().minusMonths(1), LocalDateTime.now());
		
		assertEquals(2, productReports.size());
	}

}
