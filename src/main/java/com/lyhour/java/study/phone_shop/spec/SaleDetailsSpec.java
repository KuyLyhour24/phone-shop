package com.lyhour.java.study.phone_shop.spec;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import com.lyhour.java.study.phone_shop.entity.Sale;
import com.lyhour.java.study.phone_shop.entity.SaleDetail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Data
public class SaleDetailsSpec implements Specification<SaleDetail> {
	private SaleDetailsFilter detailsFilter;

	@Override
	@Nullable
	public Predicate toPredicate(Root<SaleDetail> saleDetail, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicates= new ArrayList<>();
		Join<SaleDetail, Sale> sale = saleDetail.join("sale");
		if(Objects.nonNull(detailsFilter.getStartDate())) {
			cb.greaterThanOrEqualTo(sale.get("soldDate"), detailsFilter.getStartDate());
		}
		if(Objects.nonNull(detailsFilter.getEndDate())){
			cb.lessThanOrEqualTo(sale.get("soldDate"), detailsFilter.getEndDate());
		}
		Predicate predicate = cb.and(predicates.toArray(Predicate[]::new));
		return predicate;
	}

}
