package com.content_manager_service.dao;

import com.model.PerformanceEvaluationCriteriaScoreVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


@Mapper
public interface PerformanceEvaluationCriteriaScoreDao {

    int createPerformanceEvaluationCriteriaScore(PerformanceEvaluationCriteriaScoreVO performanceEvaluationCriteriaScoreVO);

    List<Map> getAllUsersPerformanceEvaluations(@Param("userId") Long userId,
                                                @Param("quarters") List<Integer> quarters,
                                                @Param("sprints") List<Integer> sprints,
                                                @Param("year") Integer year,
                                                @Param("groupBy") String groupBy);

    List<Map> getUserPerformanceScores(@Param("userId")Long userId,
                                                                        @Param("year")Integer year,
                                                                        @Param("type") Integer type,
                                                                        @Param("key") Integer key,
                                                                        @Param("keys") List<Integer> keys);

    int checkIfEvaluationExists(PerformanceEvaluationCriteriaScoreVO performanceEvaluationCriteriaScoreVO);
}
