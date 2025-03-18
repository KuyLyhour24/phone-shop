package com.lyhour.java.study.phone_shop.projection;

import java.math.BigDecimal;

public interface ProductSold {
	Long getProductId();
	String getProductName();
	Integer getUnit();
	BigDecimal getTotalAmount();

}
