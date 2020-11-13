package com.forfresh.model.dto.kakaopay;

import lombok.Data;

@Data
public class TotalPayment {

	private String userId; 
	private String refrigNo;
	private String shoplistNo;  //장바구니 pk
	private String stockList;   //장바구니 품목 여러개  stockList "1 1 2 1"
	private String productNo;   //product"0 1 2 3"
	private String itemName;    //카테고리 "돼지고기" pk 이름으로 찾으면 됨  
	private String priceList; // "1000 10000 200 10"
	private String quantity;    
	private String totalAmount; 
}
