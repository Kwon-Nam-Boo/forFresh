package com.forfresh.model.dao.refrig;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.io.IOException;
import java.time.LocalDateTime;

import com.forfresh.model.dto.refrig.Foodlist;

public interface FoodlistDao extends JpaRepository<Foodlist, String> {

    // SELECT * FROM food_list JOIN expiration ON food_list.category_no = expiration.category_no;

    @Query(value="SELECT *"
    + " e.category as category"
    + ", e.expire_date as expireDate"
    + " FROM food_list as f JOIN expiration as e ON f.category_no = e.category_no",nativeQuery=true)
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
    String getItemInfo(String foodName) throws IOException;

    @Transactional
    void deleteByFoodNo(int foodNo);
}
