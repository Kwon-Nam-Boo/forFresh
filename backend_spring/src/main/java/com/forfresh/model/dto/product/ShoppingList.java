package com.forfresh.model.dto.product;

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
@Table(name = "shopping_list")
public class ShoppingList {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer shoplistNo;
    
    private String userId;
    private Integer productNo;
    
}
