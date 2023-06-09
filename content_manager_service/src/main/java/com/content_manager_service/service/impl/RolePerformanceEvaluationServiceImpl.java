package com.content_manager_service.service.impl;


import com.content_manager_service.dao.PerformanceEvaluationCriteriaScoreDao;
import com.content_manager_service.dao.PerformanceFeedbackDao;
import com.content_manager_service.dao.RolePerformanceEvaluationDao;
import com.content_manager_service.service.RolePerformanceEvaluationService;
import com.model.PerformanceEvaluationCriteriaScoreVO;
import com.model.PerformanceFeedbackVO;
import com.model.RolePerformanceEvaluationVO;
import com.util.APIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RolePerformanceEvaluationServiceImpl implements RolePerformanceEvaluationService {

    private final RolePerformanceEvaluationDao rolePerformanceEvaluationDao;

    private final PerformanceEvaluationCriteriaScoreDao performanceEvaluationCriteriaScoreDao;

    private final PerformanceFeedbackDao performanceFeedbackDao;

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
    public ResponseEntity addPerformanceFeedback(PerformanceFeedbackVO performanceFeedbackVO) {
        int result = performanceFeedbackDao.addPerformanceFeedback(performanceFeedbackVO);
        if(result > 0){
            return APIResponse.resultSuccess("Performance successfully added. ");
        }
        return APIResponse.resultFail();
    }

    @Override
    public ResponseEntity getUserPerformanceEvaluationPoints(Long userId, List<Integer> quarter, List<Integer> sprint, Integer year, String groupBy) {
        List<Map> scores = new ArrayList<>();
        List<Map> userPerformances = performanceEvaluationCriteriaScoreDao.getAllUsersPerformanceEvaluations(userId,quarter,sprint,year,groupBy);
        if(userPerformances.isEmpty()){
            return APIResponse.resourceNotFound();
        }
        for(Map userPerformance : userPerformances){
            if(userPerformance.get("type").equals(1)) //1 for quarter , 0 for sprint
                scores = performanceEvaluationCriteriaScoreDao.getUserPerformanceScores(userId,year,(Integer) userPerformance.get("type"),(Integer) userPerformance.get("key"),sprint);
            else
                scores = performanceEvaluationCriteriaScoreDao.getUserPerformanceScores(userId,year,(Integer) userPerformance.get("type"),(Integer) userPerformance.get("key"),quarter);

            userPerformance.put("performance",scores);
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
