package com.lyhour.java.study.phone_shop.repository;

import org.springframework.stereotype.Repository;

import com.lyhour.java.study.phone_shop.entity.Brand;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {

}
