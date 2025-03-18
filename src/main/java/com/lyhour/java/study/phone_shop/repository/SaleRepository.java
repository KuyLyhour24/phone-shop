package com.lyhour.java.study.phone_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lyhour.java.study.phone_shop.entity.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

}
