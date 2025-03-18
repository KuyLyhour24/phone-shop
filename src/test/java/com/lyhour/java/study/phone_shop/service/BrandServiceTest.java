package com.lyhour.java.study.phone_shop.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.lyhour.java.study.phone_shop.entity.Brand;
import com.lyhour.java.study.phone_shop.exception.ResourceNotFoundException;
import com.lyhour.java.study.phone_shop.repository.BrandRepository;
import com.lyhour.java.study.phone_shop.service.impl.BrandSerivceImpl;

@ExtendWith(MockitoExtension.class)
public class BrandServiceTest {
	
	@Mock
	private BrandRepository brandRepository;
	
	private BrandService brandService;
	
	//before run all test cases, need to execute this statement
	@BeforeEach
	public void setUp() {
		brandService= new BrandSerivceImpl(brandRepository);
		
	}
	/*
	@Test
	public void testCreate() {
		//Given
		Brand brand= new Brand();
		brand.setName("Apple");
		brand.setId(1);
		//brandRepository.save(brand);
		
		//when
		when(brandRepository.save(any(Brand.class))).thenReturn(brand);
		Brand brandReturn = brandService.create(new Brand());
		//Then
		assertEquals(1, brandReturn.getId());
		assertEquals("Apple", brandReturn.getName());
		
	}*/
	@Test
	public void testCreate() {
		//Given
		Brand brand= new Brand();
		brand.setName("Apple");
		
		//When
		brandService.create(brand);
		//Then
		verify(brandRepository, times(1)).save(brand);
		//error
		//verify(brandRepository, times(1)).delete(brand);
	}
	@Test
	public void testGetById() {
		//Given 
		Brand brand= new Brand();
		brand.setName("Apple");
		brand.setId(1);
		
		//When
		when(brandRepository.findById(1)).thenReturn(Optional.of(brand));
		Brand brandReturn = brandService.getById(1);
		//Then
		
		assertEquals(1, brandReturn.getId());
		assertEquals("Apple", brandReturn.getName());	
	}
	@Test
	public void testGetByIdThrow() {
		
		//when
		when(brandRepository.findById(2)).thenReturn(Optional.empty());
		//brandService.getById(2);
		assertThatThrownBy(()->brandService.getById(2))
			.isInstanceOf(ResourceNotFoundException.class)
			.hasMessage("Brand With id = 2 not found");
		//then
	}

}
