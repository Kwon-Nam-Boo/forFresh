package com.forfresh.model.dto.refrig;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@IdClass(RefrigShareId.class)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "refrig_share")

public class RefrigShare {
    
    @Id    
    private int refrigNo;
    @Id
    private String sharedId;
    private int accept;

}
