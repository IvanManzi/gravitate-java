package com.content_manager_service.dao;

import com.model.WishVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface WishDao {

    int createWish(WishVO wishVO);
    List<WishVO> getLatestWishByUserId(Long userId);
    List<Map> getTeamMembersLatestWishes(@Param("wishType") String wishType,@Param("date") Date date);
    List<Map> getAllWishes(@Param("wishType") String wishType,
                           @Param("date") Date date,
                           @Param("search") String search,
                           @Param("userLevel") String userLevel,
                           @Param("userId")Long userId);
    int updateWish(WishVO wishVO);
    int deleteWish(Long wishId);

}
