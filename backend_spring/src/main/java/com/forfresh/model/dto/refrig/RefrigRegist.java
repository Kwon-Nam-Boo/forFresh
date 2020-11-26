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
@Table(name = "refrig_regist")

public class RefrigRegist {

    @Id
    private int refrigNo;
    
    private String userId;
    private String refrigName;
    
}
