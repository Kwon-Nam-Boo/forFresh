package com.forfresh.model.dao.kakaopay;

import org.springframework.data.jpa.repository.JpaRepository;

import com.forfresh.model.dto.kakaopay.PaymentList;

public interface PaymentListDao extends JpaRepository<PaymentList, Integer>{

}
