package com.content_manager_service.controller;

import com.content_manager_service.form.CreateWishRequest;
import com.content_manager_service.form.UpdateWishRequest;
import com.content_manager_service.service.GravitateWallOfWishesService;
import com.model.WishVO;
import com.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("api/v1/content/wish")
@RequiredArgsConstructor
public class GravitateWallOfWishesController {

    private final GravitateWallOfWishesService gravitateWallOfWishesService;

    @PostMapping(value = "/create")
    public ResponseEntity createWish(@Valid @RequestBody CreateWishRequest createWishRequest, HttpServletRequest request) throws IOException {
        WishVO wishVO = new WishVO();
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        wishVO.setAdminId(Long.valueOf(userId));
        wishVO.setUserId(createWishRequest.userId());
        wishVO.setComment(createWishRequest.comment());
        return gravitateWallOfWishesService.createWish(wishVO);
    }

    @GetMapping(value = "/all")
    public ResponseEntity getAllWishes(HttpServletRequest request) throws IOException {
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        return gravitateWallOfWishesService.getAllWishes(Long.valueOf(userId));
    }

    @PutMapping(value = "/")
    public ResponseEntity updateWish(@Valid @RequestBody UpdateWishRequest updateWishRequest,HttpServletRequest request) throws IOException {
        WishVO wishVO = new WishVO();
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        wishVO.setAdminId(Long.valueOf(userId));
        wishVO.setWishId(updateWishRequest.wishId());
        wishVO.setUserId(updateWishRequest.userId());
        wishVO.setWithType(updateWishRequest.wishType());
        wishVO.setComment(updateWishRequest.comment());
        return gravitateWallOfWishesService.updateWish(wishVO);
    }

    @DeleteMapping(value = "/{wishId}")
    public ResponseEntity deleteWish(@PathVariable("wishId") Long wishId){
        return gravitateWallOfWishesService.deleteWish(wishId);
    }

}
