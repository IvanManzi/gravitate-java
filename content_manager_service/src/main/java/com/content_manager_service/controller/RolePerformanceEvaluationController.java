package com.content_manager_service.controller;

import com.content_manager_service.form.CreateRolePerformanceRequest;
import com.content_manager_service.form.UpdateRolePerformanceRequest;
import com.content_manager_service.service.RolePerformanceEvaluationService;
import com.model.RolePerformanceEvaluationVO;
import com.util.APIResponse;
import com.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.io.IOException;

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


}
