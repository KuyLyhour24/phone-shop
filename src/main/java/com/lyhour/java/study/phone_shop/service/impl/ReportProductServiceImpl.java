package com.lyhour.java.study.phone_shop.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.lyhour.java.study.phone_shop.dto.ProductReportDTO;
import com.lyhour.java.study.phone_shop.entity.Product;
import com.lyhour.java.study.phone_shop.entity.SaleDetail;
import com.lyhour.java.study.phone_shop.projection.ProductSold;
import com.lyhour.java.study.phone_shop.repository.ProductRepository;
import com.lyhour.java.study.phone_shop.repository.SaleDetailRepository;
import com.lyhour.java.study.phone_shop.service.ReportService;
import com.lyhour.java.study.phone_shop.spec.SaleDetailsFilter;
import com.lyhour.java.study.phone_shop.spec.SaleDetailsSpec;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportProductServiceImpl implements ReportService {
	private final SaleDetailRepository saleDetailRepository;
	private final ProductRepository productRepository;
	

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
		
		List<Long> productIds = saleDetails.stream()
			.map(sd-> sd.getProduct().getId())
			.toList();
		Map<Long, Product> productMap = productRepository.findAllById(productIds).stream()
			.collect(Collectors.toMap(Product::getId, Function.identity()));
		
		Map<Product, List<SaleDetail>> saleDetailMap = saleDetails.stream()
			.collect(Collectors.groupingBy(SaleDetail::getProduct));
		
		for(var entry: saleDetailMap.entrySet()) {
			Product product = productMap.get(entry.getKey().getId());
			List<SaleDetail> sdList = entry.getValue();
			
			//get unit
			Integer unit = sdList.stream().map(SaleDetail::getUnit)
					.reduce(0,(a,b)-> a+b);
			
			double totalAmount= sdList.stream().mapToDouble(sd-> sd.getUnit()*sd.getAmount().doubleValue()).sum();
			
			ProductReportDTO reportDTO = new ProductReportDTO();
			
			reportDTO.setProductId(product.getId());
			reportDTO.setProductName(product.getName());
			reportDTO.setUnit(unit);
			reportDTO.setTotalAmount(BigDecimal.valueOf(totalAmount));
			
			list.add(reportDTO);
			
		}

		return list;
	}

}
