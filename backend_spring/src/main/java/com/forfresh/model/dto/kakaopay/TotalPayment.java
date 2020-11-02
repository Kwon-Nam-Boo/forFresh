package com.forfresh.model.dto.kakaopay;

import lombok.Data;

@Data
public class TotalPayment {

	private String itemName;
	private String quantity;
	private String totalAmount;
}
