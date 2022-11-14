package com.content_manager_service.service.impl;

import com.content_manager_service.dao.WishDao;
import com.content_manager_service.service.GravitateWallOfWishesService;
import com.model.WishVO;
import com.util.APIResponse;
import com.util.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.util.Constants.*;

@Service
@RequiredArgsConstructor
public class GravitateWallOfWishesServiceImpl implements GravitateWallOfWishesService {

    private final WishDao wishDao;

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
    public ResponseEntity getAllWishes(Long userId) {
        List<Map> wishes = wishDao.getAllWishes(userId);
        if(wishes.isEmpty()){
            return APIResponse.resultFail("No wishes found. ");
        }else{
            Map<String,Object> data = new HashMap<>();
            data.put("WISHES",wishes);
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
