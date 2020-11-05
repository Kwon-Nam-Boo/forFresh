package com.forfresh.model.dto.product;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_comment")
public class ProductComment {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pcommentNo;
    
    private String userId;
    private Integer productNo;
    private Integer userRate;
    private String commentDetail;
    private LocalDateTime registDate; 

}
