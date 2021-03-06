package com.forfresh.model.dto.kakaopay;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payment_list")
public class PaymentList {
	@Id
	private String tid;
	
	private String userId;
	private String productNo;
	private Integer quantity;
	private Integer totalAmount;
	private LocalDateTime registDate; 
	
}
