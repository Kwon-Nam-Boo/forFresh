package com.forfresh.model.dto.product;

import java.time.LocalDateTime;


import org.springframework.web.multipart.MultipartFile;


import lombok.Data;

@Data
public class ProductPass {
	
    private Integer categoryNo;
    private String productName;
    private Integer productPrice;
    private Integer stock;
    private String description;
    private LocalDateTime registDate;
    private Integer avgRate;
    private MultipartFile thumnailFile;
    private MultipartFile detailFile;
}
