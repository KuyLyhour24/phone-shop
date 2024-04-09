package com.lyhour.java.study.phone_shop.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.lyhour.java.study.phone_shop.entity.Brand;
@DataJpaTest
public class BrandRepositoryTest {

	@Autowired
	private BrandRepository brandRepository;

	@Test
	public void testFindByNameLike() {
		//given
		Brand brand = new Brand();
		brand.setName("Apple");
		
		Brand brand2 = new Brand();
		brand2.setName("Samsung");
		
		brandRepository.save(brand);
		brandRepository.save(brand2);
		
		//when
		List<Brand> brands = brandRepository.findByNameLike("%A%");
		
		//then
		assertEquals(1, brands.size());
		assertEquals("Apple", brands.get(0).getName());
		assertEquals(1, brands.get(0).getId());
	}
	@Test
	public void testFindByNameContaining() {
		Brand brand = new Brand();
		brand.setName("Banana");
		brandRepository.save(brand);
		List<Brand> brands = brandRepository.findByNameContaining("B");
		
		assertEquals(1, brands.size());
	}
}
