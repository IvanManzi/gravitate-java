package com.content_manager_service.service;

import com.model.RolePerformanceEvaluationVO;
import com.util.APIResponse;
import org.springframework.http.ResponseEntity;

public interface RolePerformanceEvaluationService {

    ResponseEntity createPerformanceEvaluation(RolePerformanceEvaluationVO rolePerformanceEvaluationVO);

    ResponseEntity getAllRolePerformanceEvaluation();

    ResponseEntity updateRolePerformanceEvaluation(RolePerformanceEvaluationVO rolePerformanceEvaluationVO);

    ResponseEntity deleteRolePerformanceEvaluation(Long performanceEvaluationId);
}
