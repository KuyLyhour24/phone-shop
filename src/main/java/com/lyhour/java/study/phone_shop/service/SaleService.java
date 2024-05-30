package com.lyhour.java.study.phone_shop.service;

import com.lyhour.java.study.phone_shop.dto.SalesDTO;
import com.lyhour.java.study.phone_shop.entity.Sale;

public interface SaleService {
	
	void sell(SalesDTO salesDTO);
	Sale getById(Long saleId);
	void cancelSale(Long saleId);

}
