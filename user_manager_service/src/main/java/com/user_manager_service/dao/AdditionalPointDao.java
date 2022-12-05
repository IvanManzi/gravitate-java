package com.user_manager_service.dao;

import com.model.AdditionalPointVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface AdditionalPointDao {

    int createAdditionalPoint(AdditionalPointVO additionalPointVO);

    List<Map> getUserAdditionalPoints(@Param("userId") Long userId,
                                      @Param("quarter") String quarter,
                                      @RequestParam("date") Date date);
}
