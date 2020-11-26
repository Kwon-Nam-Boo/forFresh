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
