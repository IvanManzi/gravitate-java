package com.content_manager_service.service.impl;


import com.content_manager_service.dao.RolePerformanceEvaluationDao;
import com.content_manager_service.service.RolePerformanceEvaluationService;
import com.model.RolePerformanceEvaluationVO;
import com.util.APIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.util.Constants.*;

@Service
@RequiredArgsConstructor
public class RolePerformanceEvaluationServiceImpl implements RolePerformanceEvaluationService {

    private final RolePerformanceEvaluationDao rolePerformanceEvaluationDao;

    @Override
    public APIResponse createPerformanceEvaluation(RolePerformanceEvaluationVO rolePerformanceEvaluationVO) {
        int result = rolePerformanceEvaluationDao.createRolePerformanceEvaluation(rolePerformanceEvaluationVO);
        if(result > 0){
            return new APIResponse(RESOURCE_CREATED,"Performance evaluation successfully created. ");
        }else{
            return new APIResponse(BAD_REQUEST);
        }
    }

    @Override
    public APIResponse getAllRolePerformanceEvaluation(Long userId) {
        List<Map> performanceEvaluations = rolePerformanceEvaluationDao.getRolePerformanceEvaluation(userId);
        if(performanceEvaluations.isEmpty()){
            return new APIResponse(NOT_FOUND,"No performance evaluations found.");
        }else{
            Map<String,Object> data = new HashMap<>();
            data.put("PERFORMANCE_EVALUATIONS",performanceEvaluations);
            return new APIResponse(REQUEST_OK,data);
        }
    }

    @Override
    public APIResponse updateRolePerformanceEvaluation(RolePerformanceEvaluationVO rolePerformanceEvaluationVO) {
        int result = rolePerformanceEvaluationDao.updateRolePerformanceEvaluation(rolePerformanceEvaluationVO);
        if(result > 0){
            return new APIResponse(REQUEST_OK,"Performance evaluation successfully updated. ");
        }else{
            return new APIResponse(BAD_REQUEST);
        }
    }

    @Override
    public APIResponse deleteRolePerformanceEvaluation(Long performanceEvaluationId) {
        int result = rolePerformanceEvaluationDao.deleteRolePerformanceEvaluation(performanceEvaluationId);
        if(result > 0){
            return new APIResponse(REQUEST_OK,"Performance evaluation successfully deleted.");
        }else{
            return new APIResponse(BAD_REQUEST);
        }
    }
}
