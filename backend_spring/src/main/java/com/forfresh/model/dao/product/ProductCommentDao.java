package com.forfresh.model.dao.product;

import org.springframework.data.jpa.repository.JpaRepository;

import com.forfresh.model.dto.product.ProductComment;

public interface ProductCommentDao extends JpaRepository<ProductComment, Integer>{

}
