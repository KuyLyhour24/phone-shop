package com.lyhour.java.study.phone_shop.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.lyhour.java.study.phone_shop.dto.ExpenseReportDTO;
import com.lyhour.java.study.phone_shop.dto.ProductReportDTO;
import com.lyhour.java.study.phone_shop.entity.Product;
import com.lyhour.java.study.phone_shop.entity.ProductImportHistory;
import com.lyhour.java.study.phone_shop.entity.SaleDetail;
import com.lyhour.java.study.phone_shop.projection.ProductSold;
import com.lyhour.java.study.phone_shop.repository.ProductImportRepository;
import com.lyhour.java.study.phone_shop.repository.ProductRepository;
import com.lyhour.java.study.phone_shop.repository.SaleDetailRepository;
import com.lyhour.java.study.phone_shop.service.ReportService;
import com.lyhour.java.study.phone_shop.spec.ProductImportHistoriesFilter;
import com.lyhour.java.study.phone_shop.spec.ProductImportHistoriesSpec;
import com.lyhour.java.study.phone_shop.spec.SaleDetailsFilter;
import com.lyhour.java.study.phone_shop.spec.SaleDetailsSpec;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportProductServiceImpl implements ReportService {
	private final SaleDetailRepository saleDetailRepository;
	private final ProductRepository productRepository;
	private final ProductImportRepository importRepository;

	@Override
	public List<ProductSold> getProductReport(LocalDateTime startDate, LocalDateTime endDate) {
		List<ProductSold> productSold = saleDetailRepository.findProductSold(startDate, endDate);
		return productSold;
	}

	@Override
	public List<ProductReportDTO> getProductReports(LocalDateTime startDate, LocalDateTime endDate) {
		// TODO Auto-generated method stub
		List<ProductReportDTO> list = new ArrayList<>();
		SaleDetailsFilter detailsFilter = new SaleDetailsFilter();
		detailsFilter.setStartDate(startDate);
		detailsFilter.setEndDate(endDate);

		Specification<SaleDetail> spec = new SaleDetailsSpec(detailsFilter);
		List<SaleDetail> saleDetails = saleDetailRepository.findAll(spec);

		List<Long> productIds = saleDetails.stream().map(sd -> sd.getProduct().getId()).toList();
		Map<Long, Product> productMap = productRepository.findAllById(productIds).stream()
				.collect(Collectors.toMap(Product::getId, Function.identity()));

		Map<Product, List<SaleDetail>> saleDetailMap = saleDetails.stream()
				.collect(Collectors.groupingBy(SaleDetail::getProduct));

		for (var entry : saleDetailMap.entrySet()) {
			Product product = productMap.get(entry.getKey().getId());
			List<SaleDetail> sdList = entry.getValue();

			// get unit
			Integer unit = sdList.stream().map(SaleDetail::getUnit).reduce(0, (a, b) -> a + b);

			double totalAmount = sdList.stream().mapToDouble(sd -> sd.getUnit() * sd.getAmount().doubleValue()).sum();

			ProductReportDTO reportDTO = new ProductReportDTO();

			reportDTO.setProductId(product.getId());
			reportDTO.setProductName(product.getName());
			reportDTO.setUnit(unit);
			reportDTO.setTotalAmount(BigDecimal.valueOf(totalAmount));

			list.add(reportDTO);

		}

		return list;
	}

	@Override
	public List<ExpenseReportDTO> getExpenseReports(LocalDateTime startDate, LocalDateTime endDate) {

		ProductImportHistoriesFilter importHistoriesFilter = new ProductImportHistoriesFilter();
		importHistoriesFilter.setStartDate(startDate);
		importHistoriesFilter.setEndDate(endDate);
		Specification<ProductImportHistory> spec = new ProductImportHistoriesSpec(importHistoriesFilter);

		List<ProductImportHistory> importHistories = importRepository.findAll(spec);

		List<Long> productIds = importHistories.stream().map(ph -> ph.getProduct().getId()).toList();
		// Other way to get product id
//		Set<Long> productIds = importHistories.stream()
//			.map(ph-> ph.getProduct().getId())
//			.collect(Collectors.toSet());

		Map<Long, Product> productMap = productRepository.findAllById(productIds).stream()
				.collect(Collectors.toMap(Product::getId, Function.identity()));

		Map<Product, List<ProductImportHistory>> importHistoryMap = importHistories.stream()
				.collect(Collectors.groupingBy(ProductImportHistory::getProduct));

		// List<ExpenseReportDTO> list = new ArrayList<>();
		var expenseReportDTOs = new ArrayList<ExpenseReportDTO>();

		for (var entry : importHistoryMap.entrySet()) {
			Product product = productMap.get(entry.getKey().getId());
			List<ProductImportHistory> importHistoryList = entry.getValue();
			
			
			//int sum = importHistoryList.stream().mapToInt(imp -> imp.getImportUnit()).sum();
			Integer totalUnit = importHistoryList.stream().map(ProductImportHistory::getImportUnit).reduce(0,
					(a, b) -> a + b);
			
			double totalAmount = importHistoryList.stream()
					.mapToDouble(imp -> imp.getImportUnit() * imp.getPricePerUnit().doubleValue()).sum();

			var expenseReportDTO = new ExpenseReportDTO();
			expenseReportDTO.setProductId(product.getId());
			expenseReportDTO.setProductName(product.getName());
			expenseReportDTO.setTotalUnit(totalUnit);
			expenseReportDTO.setTotalAmount(BigDecimal.valueOf(totalAmount));

			expenseReportDTOs.add(expenseReportDTO);

		}
		Collections.sort(expenseReportDTOs, (a,b) ->(int)(a.getProductId() - b.getProductId()));

		return expenseReportDTOs;
	}

}
