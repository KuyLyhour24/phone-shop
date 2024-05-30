package com.lyhour.java.study.phone_shop.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lyhour.java.study.phone_shop.dto.ProductImportDTO;
import com.lyhour.java.study.phone_shop.entity.Product;
import com.lyhour.java.study.phone_shop.entity.ProductImportHistory;
import com.lyhour.java.study.phone_shop.exception.ApiException;
import com.lyhour.java.study.phone_shop.exception.ResourceNotFoundException;
import com.lyhour.java.study.phone_shop.mapper.ProductMapper;
import com.lyhour.java.study.phone_shop.repository.ProductImportRepository;
import com.lyhour.java.study.phone_shop.repository.ProductRepository;
import com.lyhour.java.study.phone_shop.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

	private final ProductRepository productRepository;
	private final ProductImportRepository importRepository;
	private final ProductMapper productMapper;
	
	@Override
	public Product create(Product product) {
		String name ="%s %s"
				.formatted(product.getModel().getName(),product.getColor().getName());
		product.setName(name);
		return productRepository.save(product);
	}
	@Override
	public Product getById(Long id) {
		return productRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Product", id));
	}
	@Override
	public void createImport(ProductImportDTO importDTO) {
		Product product = getById(importDTO.getProductId());
		Integer availableUnit =0;
		if(product.getAvailableUnit() !=null) {
			availableUnit = product.getAvailableUnit();	
		}
		product.setAvailableUnit(availableUnit + importDTO.getImportUnit());
		productRepository.save(product);
		
		ProductImportHistory importHistory = productMapper.toImportHistory(importDTO, product);
		importRepository.save(importHistory);
		
	
		
	}
	@Override
	public void setPrice(Long id, BigDecimal price) {
		Product product = getById(id);
		product.setSalePrice(price);
		productRepository.save(product);
		
	}
	@Override
	public Map<Integer, String> productImport(MultipartFile file) {
		Map<Integer, String> map = new HashMap<>();
		try {
			Workbook workbook = new XSSFWorkbook(file.getInputStream());
		    Sheet sheet = workbook.getSheet("Products");
			Iterator<Row> rowIterator = sheet.iterator();
			rowIterator.next(); 
			while(rowIterator.hasNext()) {
				Integer rowNumber=0;
				try {
					Row row = rowIterator.next();
					int cellIndex=0;
					
					Cell cellNo= row.getCell(cellIndex++);
					rowNumber = (int) cellNo.getNumericCellValue();
					
					Cell cellModelId= row.getCell(cellIndex++);
					Long modelId = (long) cellModelId.getNumericCellValue();
					
					
					Cell cellColorId = row.getCell(cellIndex++);
					Long colorId = (long) cellColorId.getNumericCellValue();
					
					
					Cell cellImportPrice = row.getCell(cellIndex++);
					Double importPrice = cellImportPrice.getNumericCellValue();
					
					Cell cellImportUnit = row.getCell(cellIndex++);
					Integer importUnit = (int) cellImportUnit.getNumericCellValue();
					if(importUnit<1) {
						throw new ApiException(HttpStatus.BAD_REQUEST, "Unit must be greater than 0!");
					}
					
					Cell cellDate = row.getCell(cellIndex++);
					LocalDateTime dateTime= cellDate.getLocalDateTimeCellValue();
					
					Product product = getByModelIdAndColorId(modelId, colorId);
					
					
					Integer availableUnit= 0;
					if(product.getAvailableUnit()!=null) {
						availableUnit = product.getAvailableUnit();
					}
					product.setAvailableUnit(availableUnit + importUnit);
					productRepository.save(product);
					
					ProductImportHistory importHistory = new ProductImportHistory();
					
					importHistory.setProduct(product);
					importHistory.setPricePerUnit(BigDecimal.valueOf(importPrice));
					importHistory.setImportUnit(importUnit);
					importHistory.setImportDate(dateTime);
					
					importRepository.save(importHistory);
					
					
				}catch(Exception e) {
					map.put(rowNumber, e.getMessage());
				}
			}
			
		}catch(IOException e){
			e.printStackTrace();
			
			
		}
		return map;
	}
	@Override
	public Product getByModelIdAndColorId(Long modelId, Long colorId) {
		String text = "Product with Model Id = %s and Color Id =%d was not found!";
		return productRepository.findByModelIdAndColorId(modelId, colorId)
				.orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,text.formatted(modelId, colorId) ));
	}
		
	
	

}
