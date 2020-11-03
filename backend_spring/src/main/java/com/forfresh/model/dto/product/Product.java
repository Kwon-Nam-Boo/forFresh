package com.forfresh.model.dto.product;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "testtt")
public class Product {
	
    @Id
    private Integer productNo;
    
    private Integer categoryNo;
    private String productName;
    private Integer productPrice;
    private Integer stock;
    private String description;
    private String imgUrl; 
    private LocalDateTime registDate;
    private Integer avgRate;
    private String detailImgs;
}
