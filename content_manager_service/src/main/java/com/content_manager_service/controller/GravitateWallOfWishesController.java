package com.content_manager_service.controller;

import com.content_manager_service.form.CreateWishReplyRequest;
import com.content_manager_service.form.CreateWishRequest;
import com.content_manager_service.form.UpdateWishRequest;
import com.content_manager_service.service.GravitateWishesManagerService;
import com.model.WishReplyVO;
import com.model.WishVO;
import com.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.io.IOException;
import java.util.Date;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping(value = "api/v1/content/wish",produces = "application/json")
@RequiredArgsConstructor
public class GravitateWallOfWishesController {

    private final GravitateWishesManagerService gravitateWishesManagerService;
    private String wishType;
    private Date date;

    @PostMapping(value = "/create")
    public ResponseEntity createWish(@Valid @RequestBody CreateWishRequest createWishRequest, HttpServletRequest request) throws IOException {
        WishVO wishVO = new WishVO();
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        wishVO.setAdminId(Long.valueOf(userId));
        wishVO.setWishType(createWishRequest.wishType());
        wishVO.setUserId(createWishRequest.userId());
        wishVO.setComment(createWishRequest.comment());
        return gravitateWishesManagerService.createWish(wishVO);
    }

    @PostMapping(value = "/comment")
    public ResponseEntity createWishReply(@Valid @RequestBody CreateWishReplyRequest createWishReplyRequest, HttpServletRequest request) throws IOException {
        WishReplyVO wishReplyVO = new WishReplyVO();
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        wishReplyVO.setUserId(Long.valueOf(userId));
        wishReplyVO.setWishId(createWishReplyRequest.wishId());
        wishReplyVO.setMessage(createWishReplyRequest.comment());
        return gravitateWishesManagerService.createWishReply(wishReplyVO);
    }

    @GetMapping(value = "/user")
    public ResponseEntity getWishesByUserId(HttpServletRequest request) throws IOException {
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        return gravitateWishesManagerService.getWishesByUserId(Long.valueOf(userId));
    }

    @GetMapping(value = "/team")
    public ResponseEntity getTeamLatestWishes(@RequestParam(value = "wishType",required = false) String wishType,
                                              @RequestParam(value = "date",required = false)@DateTimeFormat(pattern="yyyy-MM-dd") Date date){
        return gravitateWishesManagerService.getTeamLatestWishes(wishType,date);
    }

    @GetMapping(value = "/all")
    public ResponseEntity getAllWishes(HttpServletRequest request) throws IOException {
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        return gravitateWishesManagerService.getAllWishes(Long.valueOf(userId));
    }

    @PutMapping(value = "/")
    public ResponseEntity updateWish(@Valid @RequestBody UpdateWishRequest updateWishRequest,HttpServletRequest request) throws IOException {
        WishVO wishVO = new WishVO();
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        wishVO.setAdminId(Long.valueOf(userId));
        wishVO.setWishId(updateWishRequest.wishId());
        wishVO.setUserId(updateWishRequest.userId());
        wishVO.setWishType(updateWishRequest.wishType());
        wishVO.setComment(updateWishRequest.comment());
        return gravitateWishesManagerService.updateWish(wishVO);
    }

    @DeleteMapping(value = "/{wishId}")
    public ResponseEntity deleteWish(@PathVariable("wishId") Long wishId){
        return gravitateWishesManagerService.deleteWish(wishId);
    }

}
