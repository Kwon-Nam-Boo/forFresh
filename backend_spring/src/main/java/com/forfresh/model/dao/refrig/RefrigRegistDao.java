package com.forfresh.model.dao.refrig;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.forfresh.model.dto.refrig.RefrigRegist;

import org.springframework.transaction.annotation.Transactional;

public interface RefrigRegistDao extends JpaRepository<RefrigRegist, String> {
    List<RefrigRegist> findByUserId(String userId);
    Optional<RefrigRegist> findByRefrigNo(int refrigNo);

    @Transactional
    void deleteByRefrigNo(int refrigNo);
}
