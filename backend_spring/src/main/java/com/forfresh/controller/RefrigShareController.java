package com.forfresh.controller;

import java.util.HashMap;
import java.util.ArrayList;
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
public class RefrigShareController {

    @Autowired
    RefrigRegistDao refrigRegistDao;

    @Autowired
    RefrigShareDao refrigShareDao;

    @PostMapping("/share")
	@ApiOperation(value = "냉장고 공유하기")
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
			return new ResponseEntity<>(result,  HttpStatus.OK);
		}
	}

	@PatchMapping("/share/allow")
	@ApiOperation(value = "공유 수락하기")
	public Object ShareAllow(@RequestParam(required = true) Integer refrigNo, @RequestParam(required = true) String userId) {
		Optional<RefrigShare> refrigShareOpt = refrigShareDao.findByRefrigNoAndSharedId(refrigNo, userId); 
        BasicResponse result = new BasicResponse();
		if(refrigShareOpt.isPresent()) {
            RefrigShare refrigShare = refrigShareOpt.get();
            refrigShare.setAccept(1);
            refrigShareDao.save(refrigShare);
            result.status = true;
            result.data = "success";
            return new ResponseEntity<>(result, HttpStatus.OK);
		}
		else {
			result.status=false;
            result.data = "공유 수락 실패";
			return new ResponseEntity<>(result,  HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/share")
    @ApiOperation(value = "공유 거절")
    public Object ShareReject(@RequestParam(required = true) Integer refrigNo, @RequestParam(required = true) String userId){

		Optional<RefrigShare> refrigShareOpt = refrigShareDao.findByRefrigNoAndSharedId(refrigNo, userId); 
        
        if(refrigShareOpt.isPresent()){
            refrigShareDao.deleteByRefrigNoAndSharedId(refrigNo,userId);
            return new ResponseEntity<>("success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("fail", HttpStatus.OK);
        }

    }

    // @GetMapping("/getSharedRefrig")
	// @ApiOperation(value = "공유된 냉장고 조회하기")
	// public Object getSharedRefrig(@RequestParam(required = true) String userId) {
    //     System.out.println("삭제");
	// }
    
}
