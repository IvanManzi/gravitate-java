package com.content_manager_service.dao;


import com.model.PerformanceFeedbackVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PerformanceFeedbackDao {

    int addPerformanceFeedback(PerformanceFeedbackVO performanceFeedbackVO);

}
