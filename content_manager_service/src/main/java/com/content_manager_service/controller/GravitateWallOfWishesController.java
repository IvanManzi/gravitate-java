package com.content_manager_service.controller;

import com.content_manager_service.form.CreateWishRequest;
import com.content_manager_service.form.UpdateWishRequest;
import com.content_manager_service.service.GravitateWallOfWishesService;
import com.model.WishVO;
import com.util.APIResponse;
import com.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("api/v1/content/wish")
@RequiredArgsConstructor
public class GravitateWallOfWishesController {

    private final GravitateWallOfWishesService gravitateWallOfWishesService;

    @PostMapping(value = "/create")
    public ResponseEntity<APIResponse> createWish(@Valid @RequestBody CreateWishRequest createWishRequest, HttpServletRequest request){
        WishVO wishVO = new WishVO();
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        wishVO.setAdminId(Long.valueOf(userId));
        wishVO.setUserId(createWishRequest.userId());
        wishVO.setComment(createWishRequest.comment());
        APIResponse apiResponse = gravitateWallOfWishesService.createWish(wishVO);
        return ResponseEntity.ok(
                APIResponse.builder()
                        .statusCode(apiResponse.getStatusCode())
                        .data(apiResponse.getData())
                        .build()
        );
    }

    @GetMapping(value = "/all")
    public ResponseEntity<APIResponse> getAllWishes(HttpServletRequest request){
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        APIResponse apiResponse = gravitateWallOfWishesService.getAllWishes(Long.valueOf(userId));
        return ResponseEntity.ok(
                APIResponse.builder()
                        .statusCode(apiResponse.getStatusCode())
                        .data(apiResponse.getData())
                        .build()
        );
    }

    @PutMapping(value = "/")
    public ResponseEntity<APIResponse> updateWish(@Valid @RequestBody UpdateWishRequest updateWishRequest,HttpServletRequest request){
        WishVO wishVO = new WishVO();
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        wishVO.setAdminId(Long.valueOf(userId));
        wishVO.setWishId(updateWishRequest.wishId());
        wishVO.setUserId(updateWishRequest.userId());
        wishVO.setWithType(updateWishRequest.wishType());
        wishVO.setComment(updateWishRequest.comment());
        APIResponse apiResponse = gravitateWallOfWishesService.updateWish(wishVO);
        return ResponseEntity.ok(
                APIResponse.builder()
                        .statusCode(apiResponse.getStatusCode())
                        .data(apiResponse.getData())
                        .build()
        );
    }

    @DeleteMapping(value = "/{wishId}")
    public ResponseEntity<APIResponse> deleteWish(@PathVariable("wishId") Long wishId){
        APIResponse apiResponse = gravitateWallOfWishesService.deleteWish(wishId);
        return ResponseEntity.ok(
                APIResponse.builder()
                        .statusCode(apiResponse.getStatusCode())
                        .data(apiResponse.getData())
                        .build()
        );
    }

}
