package com.forfresh.model.dto.product;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCommentPass {
    private Integer pcommentNo;
    private String userId;
    private String nickName;
    private Integer productNo;
    private Integer userRate;
    private String commentDetail;
    private LocalDateTime registDate; 
}
