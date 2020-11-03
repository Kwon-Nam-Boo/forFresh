package com.forfresh.model.dto.user;

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
@Table(name = "user")
public class User {
    @Id
    private String userId;
    
    private String password;
    private String nickName;
    private String phone;
    private String addr1;
    private String addr2;
    private String addr3;
    
}
