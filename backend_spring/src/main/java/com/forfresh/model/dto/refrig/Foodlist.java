package com.forfresh.model.dto.refrig;

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
@Table(name = "food_list")

public class Foodlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int foodNo;
    
    private int refrigNo;
    private int categoryNo;
    private String foodName;
    private String status;
    private String registDate;
    private int stock;
    private int price;

}

