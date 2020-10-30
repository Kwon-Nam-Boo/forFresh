package com.forfresh.model.dao.product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.forfresh.model.dto.product.Product;

public interface ProductDao extends JpaRepository<Product, Integer>{

	List<Product> findByCategoryNo(Integer categoryNo, Pageable pageable);
}
