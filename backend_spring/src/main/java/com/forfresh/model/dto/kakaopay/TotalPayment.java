package com.forfresh.model.dto.kakaopay;

import lombok.Data;

@Data
public class TotalPayment {

	private String userId;
	private String productNo;
	private String itemName;
	private String quantity;
	private String totalAmount;
}
