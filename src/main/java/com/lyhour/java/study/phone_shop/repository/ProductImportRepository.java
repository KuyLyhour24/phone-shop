package com.lyhour.java.study.phone_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lyhour.java.study.phone_shop.entity.ProductImportHistory;

@Repository
public interface ProductImportRepository extends JpaRepository<ProductImportHistory, Long> {

}
