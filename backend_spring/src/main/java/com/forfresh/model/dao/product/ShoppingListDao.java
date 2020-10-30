package com.forfresh.model.dao.product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.forfresh.model.dto.product.Product;
import com.forfresh.model.dto.product.ShoppingList;

public interface ShoppingListDao extends JpaRepository<ShoppingList, Integer>{
	
	@Query(value="SELECT p.* FROM shopping_list as s JOIN product as p ON s.product_no = p.product_no  WHERE s.user_id = :userId",nativeQuery=true)
	List<Product> findByUserId(@Param("userId") String userId, Pageable pageable); 
	
	Optional<ShoppingList> findByUserIdAndProductNo(String userId, Integer productNo);
}
