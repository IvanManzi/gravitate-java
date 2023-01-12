package com.content_manager_service.dao;

import com.model.RolePerformanceEvaluationVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface RolePerformanceEvaluationDao {

    int createRolePerformanceEvaluation(RolePerformanceEvaluationVO rolePerformanceEvaluationVO);
    List<Map> getAllRolePerformanceEvaluation();
    int updateRolePerformanceEvaluation(RolePerformanceEvaluationVO rolePerformanceEvaluationVO);
    int deleteRolePerformanceEvaluation(Long performanceEvaluationId);
    List<Map> getUserRolePerformanceCriterias(Long userId);
}
