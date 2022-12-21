package com.content_manager_service.dao;

import com.model.PositionVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PositionDao {


    int savePosition(PositionVO positionVO);


    List<PositionVO> getAllPositions(String opportunityType);


    int updatePosition(PositionVO positionVO);


    int deletePosition(Long positionId);





}
