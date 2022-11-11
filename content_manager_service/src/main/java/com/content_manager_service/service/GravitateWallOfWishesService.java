package com.content_manager_service.service;

import com.model.WishVO;
import com.util.APIResponse;
import org.springframework.http.ResponseEntity;

public interface GravitateWallOfWishesService {

    ResponseEntity createWish(WishVO wishVO);

    ResponseEntity getAllWishes(Long userId);

    ResponseEntity updateWish(WishVO wishVO);

    ResponseEntity deleteWish(Long wishId);
}
