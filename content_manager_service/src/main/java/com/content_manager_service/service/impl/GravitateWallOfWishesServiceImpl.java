package com.content_manager_service.service.impl;

import com.content_manager_service.dao.WishDao;
import com.content_manager_service.service.GravitateWallOfWishesService;
import com.model.WishVO;
import com.util.APIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GravitateWallOfWishesServiceImpl implements GravitateWallOfWishesService {

    private final WishDao wishDao;

    @Override
    public APIResponse createWish(WishVO wishVO) {
        return null;
    }

    @Override
    public APIResponse getAllWishes(Long userId) {
        return null;
    }

    @Override
    public APIResponse updateWish(WishVO wishVO) {
        return null;
    }

    @Override
    public APIResponse deleteWish(Long wishId) {
        return null;
    }
}
