package com.forfresh.model.dao.refrig;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.forfresh.model.dto.refrig.Expiration;

public interface ExpirationDao extends JpaRepository<Expiration, String> {
    Optional<Expiration> findById(int categoryNo);
}
