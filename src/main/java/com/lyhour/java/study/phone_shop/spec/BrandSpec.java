package com.lyhour.java.study.phone_shop.spec;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import com.lyhour.java.study.phone_shop.entity.Brand;

import lombok.Data;

@Data
public class BrandSpec  implements Specification<Brand>{
	
	
	private final  BrandFilter brandFilter;
	
	List<Predicate> predicates = new ArrayList<>();

	@Override
	@Nullable
	public Predicate toPredicate(Root<Brand> brand, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		
		if(brandFilter.getName()!= null) {
		    Predicate name = cb.like(cb.upper(brand.get("name")), "%"+brandFilter.getName().toUpperCase()+"%");
			predicates.add(name);
		}
		if(brandFilter.getId()!= null) {
		    Predicate id = brand.get("id").in(brandFilter.getId());
			predicates.add(id);
		}
		//return cb.and(predicates.toArray(new Predicate[0]));
		return cb.and(predicates.toArray(Predicate[]::new));
	}
	
	
	
	

}
