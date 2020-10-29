package com.forfresh.model.dto.product;

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
@Table(name = "product")
public class Product {
    @Id
    private int productNo;
    
    private String categoryNo;
    private String nickNam;
    private int productPrice;
    private int stock;
    private String description;
    private String imgUrl; 
    private LocalDateTime registDate;
    private int rate;
    private String detailImgs;
}
