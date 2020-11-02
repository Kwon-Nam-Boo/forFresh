package com.forfresh.model.dto.refrig;

import javax.persistence.Column;
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
@Table(name = "food_list")

public class Foodlist {

    @Id
    private int foodNo;
    
    private int refrigNo;
    private int categoryNo;
    private String name;
    private int status;
    private String registDate;

}

