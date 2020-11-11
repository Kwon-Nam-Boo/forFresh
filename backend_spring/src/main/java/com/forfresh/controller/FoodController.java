package com.forfresh.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.forfresh.model.BasicResponse;
import com.forfresh.model.dao.refrig.FoodlistDao;
import com.forfresh.model.dao.refrig.FoodlistDao.FoodlistExpiration;
import com.forfresh.model.dto.refrig.FoodItem;
import com.forfresh.model.dto.refrig.Foodlist;
import com.forfresh.service.FoodlistService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

// @CrossOrigin(origins = { "https://i3a310.p.ssafy.io:80",
// "http://localhost:3000" })
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

    @Autowired
    FoodlistService foodlistService;
    
    @PostMapping("/regist")
    @ApiOperation(value = "음식 넣기")
    public Object save(@RequestBody(required = true) FoodItem foodItem) throws IOException {
        BasicResponse result = new BasicResponse();
        String foods = foodItem.getFoods();
        JSONArray foodsInfo = null;
        foodsInfo =  foodlistService.getItemInfo(foods);
          
        for (int i = 0; i < foodsInfo.length(); i++) {
        	JSONObject foodObject = (JSONObject) foodsInfo.get(i);
             Foodlist foodlist = new Foodlist();
             foodlist.setRefrigNo(foodItem.getRefrigNo());
             foodlist.setCategoryNo(Integer.parseInt(foodObject.get("category").toString()));
             foodlist.setFoodName(foodObject.get("foodName").toString());
             foodlist.setPrice(Integer.parseInt(foodObject.get("price").toString()));
             foodlist.setStock(Integer.parseInt(foodObject.get("count").toString()));
             foodlist.setStatus(foodObject.get("status").toString());
             foodlistDao.save(foodlist);
         }
        result.status = true;
        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @GetMapping("/getOCR")
    @ApiOperation(value = "ocr 데이터 가져오기")
    public Object getOCR(@RequestParam(required = true) String receiptUrl) throws IOException {
        BasicResponse result = new BasicResponse();
		
        String receiptInfo = null;
        receiptInfo =  foodlistService.getReceiptInfo(receiptUrl);

		if(receiptInfo != null) {
			result.status = true;
			result.object = receiptInfo;
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
		else {
			result.status=false;
			return new ResponseEntity<>(result,  HttpStatus.NOT_FOUND);
		}
    }

    @GetMapping("/getFood")
    @ApiOperation(value = "냉장고No로 food 조회")
    public Object getRefirig(@RequestParam(required = true) Integer refrigNo) {
        BasicResponse result = new BasicResponse();

        List<FoodlistExpiration> foodlist = foodlistDao.findByRefrigNo(refrigNo);

        if (!foodlist.isEmpty()) {
            result.status = true;
            result.object = foodlist;
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            result.status = false;
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
    }
    

    @GetMapping("/getFood/{foodNo}")
    @ApiOperation(value = "FoodNo로 food 조회")
    public Object getFoodNo(@PathVariable int foodNo) {
        BasicResponse result = new BasicResponse();

        Optional<FoodlistExpiration> food = foodlistDao.findByFoodNoDetail(foodNo);

        if (food.isPresent()) {
            result.status = true;
            result.object = food;
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            result.status = false;
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }

    }

    @PatchMapping("/changeFood")
    @ApiOperation(value = "음식 이름변경")

    public Object updateFood(@RequestParam(required = true) final Integer foodNo,
    @RequestParam final String foodName) {

        Optional<Foodlist> foodlistOpt = foodlistDao.findByFoodNo(foodNo);
        BasicResponse result = new BasicResponse();
        
        if (foodlistOpt.isPresent()) {
            Foodlist foodlist = foodlistOpt.get();
            foodlist.setFoodName(foodName);;
            foodlistDao.save(foodlist);
            result.status = true;
            result.data = "success";
            return new ResponseEntity<>(result, HttpStatus.OK);
            }
        else {
            result.status=false;
            result.data = "냉장고 이름 변경 실패";
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteFood")
    @ApiOperation(value = "음식 삭제하기")
    public Object deleteFood(@RequestParam(required = true) final Integer refrigNo,
            @RequestParam(required = true) final String foodName) {

        Optional<Foodlist> foodlistOpt = foodlistDao.findByRefrigNoAndFoodName(refrigNo, foodName);
        BasicResponse result = new BasicResponse();

        if (foodlistOpt.isPresent()) {
            foodlistDao.deleteByFoodNo(foodlistOpt.get().getFoodNo());
            result.status = true;
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            result.status = false;
            result.data = "음식 삭제 실패";
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
    }

    
}
