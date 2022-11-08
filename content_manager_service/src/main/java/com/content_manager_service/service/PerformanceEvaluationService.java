package com.content_manager_service.service;

import com.model.RolePerformanceEvaluationVO;
import com.util.APIResponse;

public interface PerformanceEvaluationService {

    APIResponse createPerformanceEvaluation(RolePerformanceEvaluationVO rolePerformanceEvaluationVO);

    APIResponse getAllRolePerformanceEvaluation(Long userId);

    APIResponse updateRolePerformanceEvaluation(RolePerformanceEvaluationVO rolePerformanceEvaluationVO);

    APIResponse deleteRolePerformanceEvaluation(Long performanceEvaluationId);
}
