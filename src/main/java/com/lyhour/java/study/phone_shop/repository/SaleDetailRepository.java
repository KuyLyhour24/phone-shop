package com.lyhour.java.study.phone_shop.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lyhour.java.study.phone_shop.dto.ProductReportDTO;
import com.lyhour.java.study.phone_shop.entity.SaleDetail;
import com.lyhour.java.study.phone_shop.projection.ProductSold;

@Repository
public interface SaleDetailRepository extends JpaRepository<SaleDetail, Long>, JpaSpecificationExecutor<SaleDetail> {
	
	List<SaleDetail> findBySaleId(Long saleId);
	
	@Query(value="Select p.product_id as productId ,p.product_name productName, sum(sd.unit) as unit, sum(sd.unit * sd.sold_price) as totalAmount from sale_details sd\r\n"
			+ "Inner Join sales s on sd.sale_id = s.sale_id\r\n"
			+ "Inner Join products p on p.product_id = sd.product_id\r\n"
			+ "where date(s.sold_date) >=:startDate and date(s.sold_date) <=:endDate\r\n"
			+ "group by p.product_id, p.product_name\r\n"
			+ "", nativeQuery = true)
	List<ProductSold> findProductSold(LocalDateTime startDate, LocalDateTime endDate);


}
