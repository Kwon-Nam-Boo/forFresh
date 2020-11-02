package com.forfresh.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.forfresh.model.BasicResponse;
import com.forfresh.model.dao.refrig.RefrigRegistDao;
import com.forfresh.model.dao.refrig.RefrigShareDao;
import com.forfresh.model.dao.refrig.FoodlistDao;
import com.forfresh.model.dto.refrig.Expiration;
import com.forfresh.model.dto.refrig.Foodlist;
import com.forfresh.model.dto.refrig.RefrigRegist;
import com.forfresh.model.dto.refrig.RefrigShare;
import com.forfresh.model.dto.refrig.Refrig;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

// @CrossOrigin(origins = { "https://i3a310.p.ssafy.io:80", "http://localhost:3000" })
@CrossOrigin(origins = { "*" })
@RequestMapping("food")
@RestController
public class FoodController {

    // @Autowired
    // RefrigRegistDao refrigRegistDao;

    // @Autowired
    // RefrigShareDao refrigShareDao;

    @Autowired
    FoodlistDao foodlistDao;

    @PostMapping("/register")
	@ApiOperation(value = "음식 넣기")
	public Object save(@RequestParam(required = true) Integer refrigNo, @RequestParam(required = true) List<String> foodNameList) {
        BasicResponse result = new BasicResponse();
        // List<Foodlist> foodLists = new ArrayList<>();
        for (int i=0; i<foodNameList.size(); i++){
            Foodlist foodlist = new Foodlist();
            foodlist.setRefrigNo(refrigNo);
            String foodName = foodNameList.get(i);
            foodlist.setFoodName(foodName);
            foodlist.setCategoryNo(2);
            foodlist.setStatus(1);
            foodlistDao.save(foodlist);
            // foodLists.add(foodlist);
        }

        // System.out.println(foodLists.get(0));
        // System.out.println(foodLists.get(1));

        // foodlistDao.saveAll(foodLists);
        result.status = true;

        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    
}
