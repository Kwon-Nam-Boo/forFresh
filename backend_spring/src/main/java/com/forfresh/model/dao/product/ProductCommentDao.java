package com.forfresh.model.dao.product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.forfresh.model.dto.product.ProductComment;

public interface ProductCommentDao extends JpaRepository<ProductComment, Integer>{
	
	Optional<List<ProductComment>> findByProductNo(Integer productNo, Pageable pageable);
	
	@Query(value="select avg(user_rate) from product_comment where product_no = :productNo",nativeQuery= true)
	Integer productAvgRate(@Param("productNo") Integer productNo);
}
