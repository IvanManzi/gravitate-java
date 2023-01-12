package com.content_manager_service.service;

import com.model.PerformanceEvaluationCriteriaScoreVO;
import com.model.RolePerformanceEvaluationVO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RolePerformanceEvaluationService {

    ResponseEntity createPerformanceEvaluation(RolePerformanceEvaluationVO rolePerformanceEvaluationVO);

    ResponseEntity getAllRolePerformanceEvaluation();

    ResponseEntity getUserRolePerformanceEvaluationCriterias(Long userId);

    ResponseEntity awardPointsOnPerformanceEvaluationCriteria(PerformanceEvaluationCriteriaScoreVO performanceEvaluationCriteriaScoreVO);

    ResponseEntity getUserPerformanceEvaluationPoints(Long userId, Integer quarter, Integer sprints, Integer year);

    ResponseEntity updateRolePerformanceEvaluation(RolePerformanceEvaluationVO rolePerformanceEvaluationVO);

    ResponseEntity deleteRolePerformanceEvaluation(Long performanceEvaluationId);
}
