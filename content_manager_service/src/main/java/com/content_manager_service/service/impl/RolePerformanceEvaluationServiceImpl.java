package com.content_manager_service.service.impl;


import com.content_manager_service.dao.PerformanceEvaluationCriteriaScoreDao;
import com.content_manager_service.dao.RolePerformanceEvaluationDao;
import com.content_manager_service.service.RolePerformanceEvaluationService;
import com.model.PerformanceEvaluationCriteriaScoreVO;
import com.model.RolePerformanceEvaluationVO;
import com.util.APIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RolePerformanceEvaluationServiceImpl implements RolePerformanceEvaluationService {

    private final RolePerformanceEvaluationDao rolePerformanceEvaluationDao;

    private final PerformanceEvaluationCriteriaScoreDao performanceEvaluationCriteriaScoreDao;

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
    public ResponseEntity getUserRolePerformanceEvaluationCriterias(Long userId) {
        List<Map> performanceCriterias = rolePerformanceEvaluationDao.getUserRolePerformanceCriterias(userId);
        if(performanceCriterias.isEmpty()){
            return APIResponse.resourceNotFound();
        }
        Map<String,Object> data = new HashMap<>();
        data.put("USER_PERFORMANCE_CRITERIAS",performanceCriterias);
        return APIResponse.resultSuccess(data);
    }

    @Override
    public ResponseEntity awardPointsOnPerformanceEvaluationCriteria(PerformanceEvaluationCriteriaScoreVO performanceEvaluationCriteriaScoreVO) {
        //check if no evaluation was made in the same year,quarter, sprint, and user on the specified criteria
        int check = performanceEvaluationCriteriaScoreDao.checkIfEvaluationExists(performanceEvaluationCriteriaScoreVO);
        if(check == 1){
            return APIResponse.resultFail("Evaluation on this criteria already made for the specified user. ");
        }
        int result = performanceEvaluationCriteriaScoreDao.createPerformanceEvaluationCriteriaScore(performanceEvaluationCriteriaScoreVO);
        if(result > 0){
            return APIResponse.resultSuccess("Score successfully awarded.");
        }
        return APIResponse.resultFail();
    }

    @Override
    public ResponseEntity getUserPerformanceEvaluationPoints(Long userId, List<Integer> quarter, List<Integer> sprint, Integer year, String groupBy) {
        List<Map> userPerformances = performanceEvaluationCriteriaScoreDao.getAllUsersPerformanceEvaluationCriteria(userId,quarter,sprint,year,groupBy);
        if(userPerformances.isEmpty()){
            return APIResponse.resourceNotFound();
        }
        Map<String,Object> data = new HashMap<>();
        data.put("USER_PERFORMANCES",userPerformances);
        return APIResponse.resultSuccess(data);
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
