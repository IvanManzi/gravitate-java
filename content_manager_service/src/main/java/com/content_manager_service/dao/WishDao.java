package com.content_manager_service.dao;

import com.model.WishVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface WishDao {

    int createWish(WishVO wishVO);
    List<Map> getAllWishes(Long adminId);
    int updateWish(WishVO wishVO);
    int deleteWish(Long wishId);

}
