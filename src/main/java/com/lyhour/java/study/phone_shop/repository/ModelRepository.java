package com.lyhour.java.study.phone_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lyhour.java.study.phone_shop.entity.Model;

public interface ModelRepository extends JpaRepository<Model, Integer> {
	

}
