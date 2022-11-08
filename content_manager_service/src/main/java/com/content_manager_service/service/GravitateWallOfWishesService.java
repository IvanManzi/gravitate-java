package com.content_manager_service.service;

import com.model.WishVO;
import com.util.APIResponse;

public interface GravitateWallOfWishesService {

    APIResponse createWish(WishVO wishVO);

    APIResponse getAllWishes(Long userId);

    APIResponse updateWish(WishVO wishVO);

    APIResponse deleteWish(Long wishId);
}
