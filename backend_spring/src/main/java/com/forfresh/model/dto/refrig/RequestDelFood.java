package com.forfresh.model.dto.refrig;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestDelFood {
    private List<Integer> foodNos;
}
