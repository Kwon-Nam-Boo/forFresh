package com.forfresh.model.dao.product;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.forfresh.model.dto.product.Product;
import com.forfresh.model.dto.product.ShoppingList;

public interface ShoppingListDao extends JpaRepository<ShoppingList, Integer>{
	
	@Query(value="SELECT s.shoplist_no as shoplistNo"
			+ ", p.product_no as productNo"
			+ ", p.category_no as categoryNo"
			+ ", p.product_name as productName"
			+ ", p.product_price as productPrice"
			+ ", p.stock as stock"
			+ ", p.description as description"
			+ ", p.img_url as imgUrl"
			+ ", p.regist_date as registDate"
			+ ", p.avg_rate as avgRate"
			+ ", p.detail_url as detailImgs"
			+ " FROM shopping_list as s JOIN product as p ON s.product_no = p.product_no  WHERE s.user_id = :userId",nativeQuery=true)
	List<ShopListProduct> findByUserId(@Param("userId") String userId); 
	
	public static interface ShopListProduct{
		Integer getShoplistNo(); 
		Integer getProductNo();
		Integer getCategoryNo();
		String getProductName();
		Integer getProductPrice();
		Integer getStock();
		String getDescription();
		String getImgUrl();
		LocalDateTime getRegistDate();
		Integer getAvgRate();
		String getDetailImgs();
	}
	
	Optional<ShoppingList> findByUserIdAndProductNo(String userId, Integer productNo);
}
