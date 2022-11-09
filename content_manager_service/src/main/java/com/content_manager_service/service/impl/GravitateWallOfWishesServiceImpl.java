package com.content_manager_service.service.impl;

import com.content_manager_service.dao.WishDao;
import com.content_manager_service.service.GravitateWallOfWishesService;
import com.model.WishVO;
import com.util.APIResponse;
import com.util.Constants;
import lombok.RequiredArgsConstructor;
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
    public APIResponse createWish(WishVO wishVO) {
        int result = wishDao.createWish(wishVO);
        if(result > 0){
            return new APIResponse(RESOURCE_CREATED,"Wish successfully created. ");
        }else{
            return new APIResponse(BAD_REQUEST);
        }
    }

    @Override
    public APIResponse getAllWishes(Long userId) {
        List<Map> wishes = wishDao.getAllWishes(userId);
        if(wishes.isEmpty()){
            return new APIResponse(NOT_FOUND,"No wishes found. ");
        }else{
            Map<String,Object> data = new HashMap<>();
            data.put("WISHES",wishes);
            return new APIResponse(REQUEST_OK,data);
        }
    }

    @Override
    public APIResponse updateWish(WishVO wishVO) {
        int result = wishDao.updateWish(wishVO);
        if(result > 0){
            return new APIResponse(REQUEST_OK,"Wish successfully updated.");
        }else{
            return new APIResponse(BAD_REQUEST);
        }
    }

    @Override
    public APIResponse deleteWish(Long wishId) {
        int result = wishDao.deleteWish(wishId);
        if(result > 0){
            return new APIResponse(REQUEST_OK,"Wish successfully deleted.");
        }else{
            return new APIResponse(BAD_REQUEST);
        }
    }
}
