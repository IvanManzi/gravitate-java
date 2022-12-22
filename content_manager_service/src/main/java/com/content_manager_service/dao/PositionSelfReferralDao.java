package com.content_manager_service.dao;


import com.model.PositionSelfReferralVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface PositionSelfReferralDao {

    int saveSelfReferral(PositionSelfReferralVO positionSelfReferralVO);

    List<Map> getSelfReferredPositions(@Param("userId")Long userId,@Param("userLevel") String userLevel);

    int updateSelfReferredPositionStatus(PositionSelfReferralVO positionSelfReferralVO);
}
