package com.content_manager_service.dao;

import com.model.PerformanceEvaluationCriteriaScoreVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


@Mapper
public interface PerformanceEvaluationCriteriaScoreDao {

    int createPerformanceEvaluationCriteriaScore(PerformanceEvaluationCriteriaScoreVO performanceEvaluationCriteriaScoreVO);

    List<Map> getAllUsersPerformanceEvaluationCriteria(@Param("userId") Long userId,
                                                    @Param("quarter") Integer quarter,
                                                    @Param("sprint") Integer sprints,
                                                    @Param("year") Integer year
                                                    /*@Param("groupBy") String groupBy*/);

    int checkIfEvaluationExists(PerformanceEvaluationCriteriaScoreVO performanceEvaluationCriteriaScoreVO);
}
