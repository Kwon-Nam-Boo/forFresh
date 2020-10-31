package com.forfresh.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.forfresh.model.BasicResponse;
import com.forfresh.model.dao.product.ProductCommentDao;
import com.forfresh.model.dao.product.ProductDao;
import com.forfresh.model.dao.product.ShoppingListDao;
import com.forfresh.model.dao.product.ShoppingListDao.ShopListProduct;
import com.forfresh.model.dto.product.Product;
import com.forfresh.model.dto.product.ProductComment;
import com.forfresh.model.dto.product.ShoppingList;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = { "*" })
@RequestMapping("product")
@RestController
public class ProductController {
	@Autowired
	ProductDao productDao;

	@Autowired
	ProductCommentDao productCommentDao;

	@Autowired
	ShoppingListDao shoppingListDao;

	// ****************** product CRUD (상품)
	@GetMapping("/list")
	@ApiOperation(value = "카테고리별 상품 리스트 조회")
	public ResponseEntity<List<Product>> getProductList(@RequestParam("categoryNo") Integer categoryNo,
			@RequestParam("page") Long page, @RequestParam("size") Long size, final Pageable pageable) {
		Optional<List<Product>> productList = productDao.findByCategoryNo(categoryNo, pageable);
		return new ResponseEntity<List<Product>>(productList.get(), HttpStatus.OK);
	}

	@GetMapping("/detail")
	@ApiOperation(value = "상품 상세정보 조회")
	public ResponseEntity<Product> getProductDetail(@RequestParam("productNo") Integer productNo) {
		Optional<Product> product = productDao.findById(productNo);
		return new ResponseEntity<Product>(product.get(), HttpStatus.OK);
	}

	@PostMapping("/add")
	@ApiOperation(value = "상품 추가")
	public Object saveProduct(@RequestBody Product product) {
		BasicResponse result = new BasicResponse();
		try {
			result.status = true;
			Product pd = productDao.save(product);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			result.status = false;
			return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/update")
	@ApiOperation(value = "상품 수정")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
		Optional<Product> findProduct = productDao.findById(product.getProductNo());
		findProduct.get().setAvgRate(product.getAvgRate());
		findProduct.get().setCategoryNo(product.getCategoryNo());
		findProduct.get().setDescription(product.getDescription());
		findProduct.get().setDetailImgs(product.getDetailImgs());
		findProduct.get().setImgUrl(product.getImgUrl());
		findProduct.get().setProductName(product.getProductName());
		findProduct.get().setProductPrice(product.getProductPrice());
		findProduct.get().setStock(product.getStock());
		productDao.save(findProduct.get());

		return new ResponseEntity<Product>(findProduct.get(), HttpStatus.OK);

	}

	@DeleteMapping("/delete")
	@ApiOperation(value = "상품 삭제")
	public Object deleteProduct(@RequestParam("productNo") Integer productNo) {
		BasicResponse result = new BasicResponse();
		try {
			result.status = true;
			productDao.deleteById(productNo);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			result.status = false;
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/search")
	@ApiOperation(value="상품 검색하기")
	public ResponseEntity<List<Product>> searchProduct(@RequestParam("productName")String productName,
			@RequestParam("page") Long page, @RequestParam("size") Long size, final Pageable pageable){
		Optional<List<Product>> serachProductList = productDao.searchByProductName(productName,pageable);
		return new ResponseEntity<List<Product>>(serachProductList.get(),HttpStatus.OK);
	}

	// ****************** shoppingLIST CRUD (상품 댓글)
	@GetMapping("/shop/list")
	@ApiOperation(value = "사용자 장바구니 리스트 조회")
	public ResponseEntity<List<ShopListProduct>> getUserShopList(@RequestParam("userId") String userId,
			@RequestParam("page") Long page, @RequestParam("size") Long size, final Pageable pageable) {
		List<ShopListProduct> shopList = shoppingListDao.findByUserId(userId, pageable);
		return new ResponseEntity<List<ShopListProduct>>(shopList, HttpStatus.OK);
	}

	@PostMapping("/shop/add")
	@ApiOperation(value = "사용자 상품 장바구니에 등록")
	public Object addShopList(@RequestBody ShoppingList list) {
		BasicResponse result = new BasicResponse();
		try {
			result.status = true;
			ShoppingList saveList = shoppingListDao.save(list);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			result.status = false;
			return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
	}

	
	@DeleteMapping("/shop/delete")
	@ApiOperation(value = "장바구니 리스트에서 상품 삭제")
	public Object deleteShopList(@RequestParam("shoplistNo") Integer shoplistNo) {
		BasicResponse result = new BasicResponse();
		try {
			result.status=true;
			shoppingListDao.deleteById(shoplistNo);
			return new ResponseEntity<>(result,HttpStatus.OK);
		} catch (Exception e) {
			result.status=false;
			return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);
		}
	}
	// ****************** productComment CRUD (장바구니 )
	@GetMapping("/comment/list")
	@ApiOperation(value="상품 댓글 리스트 조회")
	public ResponseEntity<List<ProductComment>> getCommentList(@RequestParam("productNo") Integer productNo, 
			@RequestParam("page") Long page, @RequestParam("size") Long size, final Pageable pageable){
		Optional<List<ProductComment>> commentList = productCommentDao.findByProductNo(productNo, pageable);
		
		return new ResponseEntity<List<ProductComment>>(commentList.get(),HttpStatus.OK);
	}
	
	@PostMapping("comment/add")
	@ApiOperation(value = "댓글 등록")
	public Object addComment(@RequestBody ProductComment comment) {
		BasicResponse result = new BasicResponse();
		try {
			result.status = true;
			ProductComment pcom = productCommentDao.save(comment);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			result.status = false;
			return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("comment/delete")
	@ApiOperation(value = "댓글 삭제")
	public Object deleteComment(@RequestParam("pcommentNo") Integer pcommentNo) {
		BasicResponse result = new BasicResponse();
		try {
			result.status=true;
			productCommentDao.deleteById(pcommentNo);
			return new ResponseEntity<>(result,HttpStatus.OK);
		} catch (Exception e) {
			result.status=false;
			return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("comment/update")
	@ApiOperation(value="댓글 수정")
	public Object updateComment(@RequestBody ProductComment comment) {
		Optional<ProductComment> findComment = productCommentDao.findById(comment.getPcommentNo());
		findComment.get().setUserRate(comment.getUserRate());
		findComment.get().setCommentDetail(comment.getCommentDetail());
		productCommentDao.save(findComment.get());
		return new ResponseEntity<ProductComment>(findComment.get(), HttpStatus.OK);
	}
}
