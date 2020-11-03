package com.forfresh.model.dao.refrig;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import java.io.IOException;

import com.forfresh.model.dto.refrig.Foodlist;

public interface FoodlistDao extends JpaRepository<Foodlist, String> {
    List<Foodlist> findByRefrigNo(int refrigNo);
    Optional<Foodlist> findByRefrigNoAndFoodName(int refrigNo, String foodName);
    String getItemInfo(String foodName) throws IOException;

    @Transactional
    void deleteByFoodNo(int foodNo);
}
