package com.content_manager_service.service.impl;


import com.content_manager_service.dao.RolePerformanceEvaluationDao;
import com.content_manager_service.service.PerformanceEvaluationService;
import com.model.RolePerformanceEvaluationVO;
import com.util.APIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerformanceEvaluationServiceImpl implements PerformanceEvaluationService {

    private final RolePerformanceEvaluationDao rolePerformanceEvaluationDao;

    @Override
    public APIResponse createPerformanceEvaluation(RolePerformanceEvaluationVO rolePerformanceEvaluationVO) {
        return null;
    }

    @Override
    public APIResponse getAllRolePerformanceEvaluation(Long userId) {
        return null;
    }

    @Override
    public APIResponse updateRolePerformanceEvaluation(RolePerformanceEvaluationVO rolePerformanceEvaluationVO) {
        return null;
    }

    @Override
    public APIResponse deleteRolePerformanceEvaluation(Long performanceEvaluationId) {
        return null;
    }
}
