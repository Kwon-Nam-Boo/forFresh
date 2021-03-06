package com.forfresh.model.dto.product;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Table(name = "product")
public class Product {
	
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productNo;
    
    private Integer categoryNo;
    private String productName;
    private Integer productPrice;
    private Integer stock;
    private String description;
    private String imgUrl; 
    private LocalDateTime registDate;
    private Integer avgRate;
    private String detailUrl;
    private Integer commentCnt;
}
