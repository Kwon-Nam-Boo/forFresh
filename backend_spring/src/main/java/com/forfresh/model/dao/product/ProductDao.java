package com.forfresh.model.dao.product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.forfresh.model.dto.product.Product;

public interface ProductDao extends JpaRepository<Product, Integer>{

	Optional<List<Product>> findByCategoryNo(Integer categoryNo, Pageable pageable);
	
	@Query(value="SELECT * FROM product WHERE product_name LIKE %:productName%",nativeQuery=true)
	Optional<List<Product>> searchByProductName(String productName, Pageable pageable);
}
