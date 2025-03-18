package com.lyhour.java.study.phone_shop.spec;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import com.lyhour.java.study.phone_shop.entity.ProductImportHistory;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductImportHistoriesSpec implements Specification<ProductImportHistory> {
	
	private ProductImportHistoriesFilter importFilter;

	@Override
	@Nullable
	public Predicate toPredicate(Root<ProductImportHistory> importHistory, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		List<Predicate> predicates = new ArrayList<>();
		if(Objects.nonNull(importFilter.getStartDate())) {
			cb.greaterThanOrEqualTo(importHistory.get("importDate"), importFilter.getStartDate());
		}
		if(Objects.nonNull(importFilter.getEndDate())) {
			cb.lessThanOrEqualTo(importHistory.get("importDate"), importFilter.getEndDate());
		}
		Predicate predicate = cb.and(predicates.toArray(Predicate[]::new));
		return predicate;
	}

}
