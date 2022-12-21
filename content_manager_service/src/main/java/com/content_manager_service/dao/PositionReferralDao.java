package com.content_manager_service.dao;

import com.model.PositionReferralVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface PositionReferralDao {


    int savePositionReferral(PositionReferralVO positionReferralVO);


    List<Map> getAllReferredPositions(@Param("userId") Long userId,@Param("userLevel") String userLevel);


    int updateReferredPosition(PositionReferralVO positionReferralVO);
}
