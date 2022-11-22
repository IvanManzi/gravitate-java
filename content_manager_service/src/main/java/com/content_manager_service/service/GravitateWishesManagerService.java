package com.content_manager_service.service;

import com.model.WishReplyVO;
import com.model.WishVO;
import org.springframework.http.ResponseEntity;

public interface GravitateWishesManagerService {

    ResponseEntity createWish(WishVO wishVO);

    ResponseEntity getAllWishes(Long adminId);

    ResponseEntity getWishesByUserId(Long userId);

    ResponseEntity createWishReply(WishReplyVO wishReplyVO);

    ResponseEntity getTeamLatestWishes();

    ResponseEntity updateWish(WishVO wishVO);

    ResponseEntity deleteWish(Long wishId);
}
