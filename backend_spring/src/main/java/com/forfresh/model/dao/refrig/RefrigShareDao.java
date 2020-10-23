package com.forfresh.model.dao.refrig;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.forfresh.model.dto.refrig.RefrigShare;

public interface RefrigShareDao extends JpaRepository<RefrigShare, String> {
    Optional<RefrigShare> findById(int shareNo);
}
