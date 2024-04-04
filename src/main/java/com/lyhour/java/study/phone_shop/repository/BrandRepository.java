package com.lyhour.java.study.phone_shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lyhour.java.study.phone_shop.entity.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {

	List<Brand> findByNameContaining(String name); 
	List<Brand> findByNameLike(String name);
}
