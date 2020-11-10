package com.forfresh.model.dao.product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.forfresh.model.dto.product.Product;

public interface ProductDao extends JpaRepository<Product, Integer>{


	@Query(value="SELECT s.shoplist_no"
			+ ", p.product_no"
			+ ", p.category_no"
			+ ", p.product_name"
			+ ", p.product_price"
			+ ", p.stock as stock"
			+ ", p.description"
			+ ", p.img_url"
			+ ", p.regist_date"
			+ ", avg(pc.user_rate) as avg_rate"
			+ ", p.detail_url"
			+ ", count(pcomment_no) as comment_cnt"
			+ " from product as p" + 
			" left outer join (select * from shopping_list where user_id=:userId) as s on p.product_no=s.product_no" + 
			" left outer join product_comment as pc on p.product_no=pc.product_no" + 
			" group by p.product_no" + 
			" having p.category_no = :categoryNo order by p.product_no desc",nativeQuery=true)
	Optional<List<Object>> findByplease(@Param("categoryNo")Integer categoryNo, @Param("userId")String userId, Pageable pageable);
//	Optional<List<Object>> findByCategoryNo(@Param("categoryNo")Integer categoryNo, Pageable pageable);


	@Query(value="SELECT * FROM product WHERE product_name LIKE %:productName%",nativeQuery=true)
	Optional<List<Product>> searchByProductName(String productName, Pageable pageable);
	
}

