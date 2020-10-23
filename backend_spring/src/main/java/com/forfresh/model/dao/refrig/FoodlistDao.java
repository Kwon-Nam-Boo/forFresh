package com.forfresh.model.dao.refrig;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.forfresh.model.dto.refrig.Foodlist;

public interface FoodlistDao extends JpaRepository<Foodlist, String> {
    Optional<Foodlist> findById(String foodNo);
}
