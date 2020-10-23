package com.forfresh.model.dao.refrig;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.forfresh.model.dto.refrig.RefrigRegist;

public interface RefrigRegistDao extends JpaRepository<RefrigRegist, String> {
    Optional<RefrigRegist> findById(int refrigNo);
}
