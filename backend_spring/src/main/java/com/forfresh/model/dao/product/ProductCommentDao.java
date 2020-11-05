package com.forfresh.model.dao.product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.forfresh.model.dto.product.ProductComment;

public interface ProductCommentDao extends JpaRepository<ProductComment, Integer>{
	
	Optional<List<ProductComment>> findByProductNo(Integer productNo, Pageable pageable);
}
