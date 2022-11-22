package com.content_manager_service.service.impl;


import com.content_manager_service.dao.RolePerformanceEvaluationDao;
import com.content_manager_service.service.RolePerformanceEvaluationService;
import com.model.RolePerformanceEvaluationVO;
import com.util.APIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity createPerformanceEvaluation(RolePerformanceEvaluationVO rolePerformanceEvaluationVO) {
        int result = rolePerformanceEvaluationDao.createRolePerformanceEvaluation(rolePerformanceEvaluationVO);
        if(result > 0){
            return APIResponse.resultSuccess("Performance evaluation successfully created. ");
        }else{
            return  APIResponse.resultSuccess();
        }
    }

    @Override
    public ResponseEntity getAllRolePerformanceEvaluation() {
        List<Map> performanceEvaluations = rolePerformanceEvaluationDao.getAllRolePerformanceEvaluation();
        if(performanceEvaluations.isEmpty()){
            return APIResponse.resultFail("No performance evaluations found.");
        }else{
            Map<String,Object> data = new HashMap<>();
            data.put("PERFORMANCE_EVALUATIONS",performanceEvaluations);
            return APIResponse.resultSuccess(data);
        }
    }

    @Override
    public ResponseEntity updateRolePerformanceEvaluation(RolePerformanceEvaluationVO rolePerformanceEvaluationVO) {
        int result = rolePerformanceEvaluationDao.updateRolePerformanceEvaluation(rolePerformanceEvaluationVO);
        if(result > 0){
            return APIResponse.resultSuccess("Performance evaluation successfully updated. ");
        }else{
            return  APIResponse.resultSuccess();
        }
    }

    @Override
    public ResponseEntity deleteRolePerformanceEvaluation(Long performanceEvaluationId) {
        int result = rolePerformanceEvaluationDao.deleteRolePerformanceEvaluation(performanceEvaluationId);
        if(result > 0){
            return APIResponse.resultSuccess("Performance evaluation successfully deleted.");
        }else{
            return APIResponse.resultFail();
        }
    }
}
