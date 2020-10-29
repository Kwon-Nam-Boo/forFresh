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
	@ApiOperation(value = "냉장고 조회")
	public Object getFood(@RequestParam("userId") String userId) {
        List<RefrigRegist> refrigList = refrigRegistDao.findByUserId(userId);
        BasicResponse result = new BasicResponse();
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
	
	@PatchMapping("/changeName")
    @ApiOperation(value = "냉장고 이름변경")

    public Object updateRefrig(@RequestParam(required = true) final Integer refrigNo,
    @RequestParam(required = true) final String refrigName) {

        Optional<RefrigRegist> refrigOpt = refrigRegistDao.findByRefrigNo(refrigNo);
        BasicResponse result = new BasicResponse();

        if (refrigOpt.isPresent()) {
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
    public Object deleteRefrig(@RequestParam(required = true) final Integer refrigNo){

		refrigRegistDao.deleteByRefrigNo(refrigNo);
		return new ResponseEntity<>(null, HttpStatus.OK);    
    }

    @PostMapping("/share")
	@ApiOperation(value = "냉장고 공유 생성")
	public Object saveShare(@RequestParam(required = true) String userId, @RequestParam(required = true) Integer refrigNo) {
		BasicResponse result = new BasicResponse();

        RefrigShare refrigShare = new RefrigShare();
        refrigShare.setSharedId(userId);
        refrigShare.setRefrigNo(refrigNo);
        
        result.status = true;
        refrigShareDao.save(refrigShare);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/getShare")
	@ApiOperation(value = "공유요청 가져오기")
	public Object getShareRequest(@RequestParam(required = true) String userId) {
        List<RefrigShare> refrigShareList = refrigShareDao.findBySharedIdAndAccept(userId, 0);
        BasicResponse result = new BasicResponse();
		if(!refrigShareList.isEmpty()) {
			result.status = true;
			result.object = refrigShareList;
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
		else {
			result.status=false;
			return new ResponseEntity<>(result,  HttpStatus.NOT_FOUND);
		}
	}
	
    
}
