package com.forfresh.model.dao.refrig;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.forfresh.model.dto.refrig.RefrigShare;

public interface RefrigShareDao extends JpaRepository<RefrigShare, String> {
    List<RefrigShare> findBySharedIdAndAccept(String sharedId, int accept);
}
