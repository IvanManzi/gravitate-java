package com.content_manager_service.service;

import com.model.PerformanceEvaluationCriteriaScoreVO;
import com.model.PerformanceFeedbackVO;
import com.model.RolePerformanceEvaluationVO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RolePerformanceEvaluationService {

    ResponseEntity createPerformanceEvaluation(RolePerformanceEvaluationVO rolePerformanceEvaluationVO);

    ResponseEntity getAllRolePerformanceEvaluation();

    ResponseEntity getUserRolePerformanceEvaluationCriterias(Long userId);

    ResponseEntity awardPointsOnPerformanceEvaluationCriteria(PerformanceEvaluationCriteriaScoreVO performanceEvaluationCriteriaScoreVO);

    ResponseEntity addPerformanceFeedback(PerformanceFeedbackVO performanceFeedbackVO);

    ResponseEntity getUserPerformanceEvaluationPoints(Long userId, List<Integer> quarter, List<Integer> sprints, Integer year, String groupBy);

    ResponseEntity updateRolePerformanceEvaluation(RolePerformanceEvaluationVO rolePerformanceEvaluationVO);

    ResponseEntity deleteRolePerformanceEvaluation(Long performanceEvaluationId);
}
