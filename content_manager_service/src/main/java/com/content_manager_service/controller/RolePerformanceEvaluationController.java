package com.content_manager_service.controller;

import com.content_manager_service.form.AwardPerformancePointsRequest;
import com.content_manager_service.form.CreateRolePerformanceRequest;
import com.content_manager_service.form.UpdateRolePerformanceRequest;
import com.content_manager_service.service.RolePerformanceEvaluationService;
import com.model.PerformanceEvaluationCriteriaScoreVO;
import com.model.RolePerformanceEvaluationVO;
import com.util.APIResponse;
import com.util.JwtUtils;
import com.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("api/v1/content/performance-evaluation")
@RequiredArgsConstructor
public class RolePerformanceEvaluationController {

    private final  RolePerformanceEvaluationService rolePerformanceEvaluationService;

    @PostMapping(value = "/create")
    public ResponseEntity<APIResponse> createPerformanceEvaluation(@Valid @RequestBody CreateRolePerformanceRequest createRolePerformanceRequest,
                                                             HttpServletRequest request) throws IOException {
        RolePerformanceEvaluationVO rolePerformanceEvaluationVO = new RolePerformanceEvaluationVO();
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        rolePerformanceEvaluationVO.setAdminId(Long.valueOf(userId));
        rolePerformanceEvaluationVO.setRoleId(createRolePerformanceRequest.roleId());
        rolePerformanceEvaluationVO.setCriteria(createRolePerformanceRequest.criteria());
        rolePerformanceEvaluationVO.setDescription(createRolePerformanceRequest.description());
        return rolePerformanceEvaluationService.createPerformanceEvaluation(rolePerformanceEvaluationVO);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<APIResponse> getAllPerformanceEvaluations() {
        return rolePerformanceEvaluationService.getAllRolePerformanceEvaluation();
    }

    @PutMapping(value = "/")
    public ResponseEntity<APIResponse> updatePerformanceEvaluation(@Valid @RequestBody UpdateRolePerformanceRequest updateRolePerformanceRequest,HttpServletRequest request) throws IOException {
        RolePerformanceEvaluationVO rolePerformanceEvaluationVO = new RolePerformanceEvaluationVO();
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        rolePerformanceEvaluationVO.setAdminId(Long.valueOf(userId));
        rolePerformanceEvaluationVO.setRolePerformanceEvaluationId(updateRolePerformanceRequest.performanceId());
        rolePerformanceEvaluationVO.setRoleId(updateRolePerformanceRequest.roleId());
        rolePerformanceEvaluationVO.setCriteria(updateRolePerformanceRequest.criteria());
        rolePerformanceEvaluationVO.setDescription(updateRolePerformanceRequest.description());
        return rolePerformanceEvaluationService.updateRolePerformanceEvaluation(rolePerformanceEvaluationVO);
    }

    @DeleteMapping(value = "/{performanceId}")
    public ResponseEntity<APIResponse> deletePerformanceEvaluation(@PathVariable("performanceId") Long performanceId){
        return rolePerformanceEvaluationService.deleteRolePerformanceEvaluation(performanceId);
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<APIResponse> getUserRolePerformanceEvaluations(@PathVariable("userId") Long userId) {
        return rolePerformanceEvaluationService.getUserRolePerformanceEvaluationCriterias(userId);
    }

    @PostMapping(value = "/award-points")
    public ResponseEntity<APIResponse> awardPointsOnUserPerformanceEvaluationCriteria(@Valid @RequestBody AwardPerformancePointsRequest awardPerformancePointsRequest, HttpServletRequest request) throws IOException {
        PerformanceEvaluationCriteriaScoreVO performanceEvaluationCriteriaScoreVO = new PerformanceEvaluationCriteriaScoreVO();
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);

        performanceEvaluationCriteriaScoreVO.setAdminId(Long.valueOf(userId));
        performanceEvaluationCriteriaScoreVO.setPerformanceEvaluationId(awardPerformancePointsRequest.performanceEvaluationId());
        performanceEvaluationCriteriaScoreVO.setUserId(awardPerformancePointsRequest.userId());
        performanceEvaluationCriteriaScoreVO.setQuarter(awardPerformancePointsRequest.quarter());
        performanceEvaluationCriteriaScoreVO.setYear(awardPerformancePointsRequest.year());
        performanceEvaluationCriteriaScoreVO.setSprint(awardPerformancePointsRequest.sprint());
        performanceEvaluationCriteriaScoreVO.setPoints(awardPerformancePointsRequest.points());
        return rolePerformanceEvaluationService.awardPointsOnPerformanceEvaluationCriteria(performanceEvaluationCriteriaScoreVO);
    }

    @GetMapping(value = "/{userId}/points")
    public ResponseEntity<APIResponse> getUserPerformanceScores(@PathVariable("userId") Long userId,
                                                           @RequestParam(value = "quarter") String quarter,
                                                           @RequestParam(value = "sprint")String sprint,
                                                           @RequestParam(value = "year") Integer year,
                                                           @RequestParam(value = "groupBy",required = false) String groupBy) {
        List<Integer> sprintValues = new ArrayList<>();
        List<Integer> quarterValues = new ArrayList<>();
        if(!ValidationUtil.isNullObject(sprint)){
            sprintValues = new ArrayList<>();
            for(String s : sprint.split(",")) {
                sprintValues.add(Integer.parseInt(s.trim()));
            }
        }
        if(!ValidationUtil.isNullObject(quarter)){
            quarterValues = new ArrayList<>();
            for(String s : quarter.split(",")) {
                quarterValues.add(Integer.parseInt(s.trim()));
            }
        }
        System.out.println(quarterValues);
        System.out.println(sprintValues);
        return rolePerformanceEvaluationService.getUserPerformanceEvaluationPoints(userId,quarterValues,sprintValues,year,groupBy);
    }



}
