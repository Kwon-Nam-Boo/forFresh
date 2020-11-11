package com.forfresh.model.dao.refrig;

import java.util.List;
import java.util.Optional;

import javax.persistence.Column;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.io.IOException;
import java.time.LocalDateTime;

import com.forfresh.model.dto.refrig.Foodlist;

public interface FoodlistDao extends JpaRepository<Foodlist, String> {

    // SELECT * FROM food_list JOIN expiration ON food_list.category_no = expiration.category_no;

    @Query(value="SELECT f.food_no as foodNo"
    + ", f.refrig_no as refrigNo"
    + ", f.category_no as categoryNo"
    + ", f.food_name as foodName"
    + ", f.status as status"
    + ", f.price as price"
    + ", f.stock as stock"
    + ", f.regist_date as registDate"
    + ", e.category as category"
    + ", e.expire_date as expireDate"
    + " FROM food_list as f JOIN expiration as e ON f.category_no = e.category_no"
    + " WHERE f.refrig_no = :refrigNo",nativeQuery=true)
    List<FoodlistExpiration> findByRefrigNo(@Param("refrigNo") int refrigNo); 

    public static interface FoodlistExpiration{
        String getCategory();
        String getExpireDate();
        Integer getFoodNo(); 
        Integer getRefrigNo();
        Integer getCategoryNo();
        String getFoodName();
        String getStatus();
        Integer getPrice();
        Integer getStock();
        String getRegistDate();
    }
    
    Optional<Foodlist> findByRefrigNoAndFoodName(int refrigNo, String foodName);
    Optional<Foodlist> findByFoodNo(int foodNo);
    String getItemInfo(String foods) throws IOException;
    String getReceiptInfo(String receiptUrl) throws IOException;

    @Transactional
    void deleteByFoodNo(int foodNo);
}
