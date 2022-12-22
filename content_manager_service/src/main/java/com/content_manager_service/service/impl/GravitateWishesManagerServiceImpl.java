package com.content_manager_service.service.impl;

import com.content_manager_service.dao.WishReplyDao;
import com.content_manager_service.dao.WishDao;
import com.content_manager_service.service.GravitateWishesManagerService;
import com.model.WishReplyVO;
import com.model.WishVO;
import com.util.APIResponse;
import com.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GravitateWishesManagerServiceImpl implements GravitateWishesManagerService {

    private final WishDao wishDao;

    private final WishReplyDao wishReplyDao;

    @Override
    public ResponseEntity createWish(WishVO wishVO) {
        int result = wishDao.createWish(wishVO);
        if(result > 0){
            return APIResponse.resultSuccess("Wish successfully created. ");
        }else{
            return APIResponse.resultFail();
        }
    }

    @Override
    public ResponseEntity getAllWishes() {
        List<Map> wishes = wishDao.getAllWishes();
        if(wishes.isEmpty()){
            return APIResponse.resultFail("No wishes found. ");
        }else{
            Map<String,Object> data = new HashMap<>();
            data.put("WISHES",wishes);
            return APIResponse.resultSuccess(data);
        }
    }

    @Override
    public ResponseEntity createWishReply(WishReplyVO wishReplyVO) {
        int result = wishReplyDao.saveWishReply(wishReplyVO);
        if(result > 0){
            return APIResponse.resultSuccess("User wish successfully created. ");
        }else{
            return APIResponse.resultFail();
        }
    }

    @Override
    public ResponseEntity getWishesByUserId(Long userId) {
        List<WishVO> wish = wishDao.getLatestWishByUserId(userId);
        if(wish.isEmpty()){
            return APIResponse.resourceNotFound();
        }else{
            Map<String,Object> data = new HashMap<>();
            data.put("WISH",wish);
            return APIResponse.resultSuccess(data);
        }
    }

    @Override
    public ResponseEntity getTeamLatestWishes(String wishType, Date date) {
        List<Map> teamWishes = wishDao.getTeamMembersLatestWishes(wishType,date);
        if(teamWishes.isEmpty()){
            return APIResponse.resourceNotFound();
        }else{
            Map<String,Object> data = new HashMap<>();
            data.put("USER_WISHES",teamWishes);
            return APIResponse.resultSuccess(data);
        }
    }

    @Override
    public ResponseEntity updateWish(WishVO wishVO) {
        int result = wishDao.updateWish(wishVO);
        if(result > 0){
            return APIResponse.resultSuccess("Wish successfully updated.");
        }else{
            return APIResponse.resultFail();
        }
    }

    @Override
    public ResponseEntity deleteWish(Long wishId) {
        int result = wishDao.deleteWish(wishId);
        if(result > 0){
            return APIResponse.resultSuccess("Wish successfully deleted.");
        }else{
            return APIResponse.resultFail();
        }
    }
}
