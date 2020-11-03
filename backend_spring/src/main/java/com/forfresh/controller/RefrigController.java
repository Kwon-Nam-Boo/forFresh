package com.forfresh.controller;

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
@RequestMapping("refrig")
@RestController
public class RefrigController {

    @Autowired
    RefrigRegistDao refrigRegistDao;

    @Autowired
    RefrigShareDao refrigShareDao;

    @PostMapping("/register")
	@ApiOperation(value = "냉장고 생성")
	public Object save(@RequestBody Refrig refrig) {
		BasicResponse result = new BasicResponse();

        RefrigRegist refrigRegist = new RefrigRegist();
        refrigRegist.setUserId(refrig.getUserId());
        refrigRegist.setRefrigName(refrig.getRefrigName());
        
        result.status = true;
        refrigRegistDao.save(refrigRegist);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/getRefrig")
	@ApiOperation(value = "id로 냉장고 조회")
	public Object getRefirig(@RequestParam("userId") String userId) {
		BasicResponse result = new BasicResponse();
		
		List<RefrigShare> refrigShareList = refrigShareDao.findBySharedIdAndAccept(userId, 1);
        List<RefrigRegist> refrigList = refrigRegistDao.findByUserId(userId);
        Optional<RefrigRegist> refrigOpt;
        for(int i=0; i<refrigShareList.size(); i++){
            refrigOpt = refrigRegistDao.findByRefrigNo(refrigShareList.get(i).getRefrigNo());
            refrigList.add(refrigOpt.get());
        }

		if(!refrigList.isEmpty()) {
			result.status = true;
			result.object = refrigList;
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
		else {
			result.status=false;
			return new ResponseEntity<>(result,  HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/getRefrig/{refrigNo}")
	@ApiOperation(value = "냉장고no로 냉장고 조회")
	public Object getRefrigByNo(@PathVariable int refrigNo) {
        Optional<RefrigRegist> refrigOpt = refrigRegistDao.findByRefrigNo(refrigNo);
        BasicResponse result = new BasicResponse();
		if(refrigOpt.isPresent()) {
			result.status = true;
			result.object = refrigOpt;
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
		else {
			result.status=false;
			return new ResponseEntity<>(result,  HttpStatus.NOT_FOUND);
		}
	}
	
	@PatchMapping("/changeName")
    @ApiOperation(value = "냉장고 이름변경")

    public Object updateRefrig(@RequestParam(required = true) final Integer refrigNo,
    @RequestParam(required = true) final String refrigName, @RequestParam(required = true) final String userId) {

        Optional<RefrigRegist> refrigOpt = refrigRegistDao.findByRefrigNo(refrigNo);
        BasicResponse result = new BasicResponse();

        if (refrigOpt.isPresent() && refrigOpt.get().getUserId()==userId) {
            RefrigRegist refrigRegist = refrigOpt.get();
            refrigRegist.setRefrigName(refrigName);
            refrigRegistDao.save(refrigRegist);
            result.status = true;
            result.data = "success";
            return new ResponseEntity<>(result, HttpStatus.OK);
		}
		else {
			result.status=false;
            result.data = "냉장고 이름 변경 실패";
			return new ResponseEntity<>(result,  HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/deleteRefrig")
    @ApiOperation(value = "냉장고 삭제하기")
    public Object deleteRefrig(@RequestParam(required = true) final Integer refrigNo, @RequestParam(required = true) final String userId){
		
		Optional<RefrigRegist> refrigOpt = refrigRegistDao.findByRefrigNo(refrigNo);
		BasicResponse result = new BasicResponse();

		if(refrigOpt.get().getUserId() == userId) {
			refrigRegistDao.deleteByRefrigNo(refrigNo);
			result.status = true;
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
		else{
			Optional<RefrigShare> refrigShareOpt = refrigShareDao.findByRefrigNoAndSharedIdAndAccept(refrigNo, userId, 1); 
			if(refrigShareOpt.isPresent()){
				refrigShareDao.deleteByRefrigNo(refrigNo);
				result.status = true;
				return new ResponseEntity<>(result, HttpStatus.OK);
			}
			else{
				result.status=false;
				result.data = "냉장고 삭제 실패";
				return new ResponseEntity<>(result,  HttpStatus.NOT_FOUND);
			}
		}    
    }
    
}
