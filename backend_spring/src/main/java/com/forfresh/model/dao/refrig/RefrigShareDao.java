package com.forfresh.model.dao.refrig;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.forfresh.model.dto.refrig.RefrigShare;

import org.springframework.transaction.annotation.Transactional;

public interface RefrigShareDao extends JpaRepository<RefrigShare, String> {
    List<RefrigShare> findBySharedIdAndAccept(String sharedId, int accept);
    Optional<RefrigShare> findByRefrigNoAndSharedId(int refrigNo, String sharedId);
    Optional<RefrigShare> findByRefrigNoAndSharedIdAndAccept(int refrigNo, String sharedId, int accept);
    
    @Transactional
    Optional<RefrigShare> deleteByRefrigNoAndSharedId(int refrigNo, String sharedId);

    @Transactional
    void deleteByRefrigNo(int refrigNo);
}
