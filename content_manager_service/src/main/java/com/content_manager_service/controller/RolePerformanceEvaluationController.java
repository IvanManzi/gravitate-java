package com.content_manager_service.controller;

import com.content_manager_service.form.CreatePolicyRequest;
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

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("api/v1/content/performance-evaluation")
@RequiredArgsConstructor
public class RolePerformanceEvaluationController {

    private final  RolePerformanceEvaluationService rolePerformanceEvaluationService;

    @PostMapping(value = "/create")
    public ResponseEntity<APIResponse> createPerformanceEvaluation(@Valid @RequestBody CreateRolePerformanceRequest createRolePerformanceRequest,
                                                             HttpServletRequest request){
        RolePerformanceEvaluationVO rolePerformanceEvaluationVO = new RolePerformanceEvaluationVO();
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        rolePerformanceEvaluationVO.setAdminId(Long.valueOf(userId));
        rolePerformanceEvaluationVO.setRoleId(createRolePerformanceRequest.roleId());
        rolePerformanceEvaluationVO.setCriteria(createRolePerformanceRequest.criteria());
        rolePerformanceEvaluationVO.setDescription(createRolePerformanceRequest.description());
        APIResponse apiResponse = rolePerformanceEvaluationService.createPerformanceEvaluation(rolePerformanceEvaluationVO);
        return ResponseEntity.ok(
                APIResponse.builder()
                        .statusCode(apiResponse.getStatusCode())
                        .data(apiResponse.getData())
                        .build()
        );
    }

    @GetMapping(value = "/all")
    public ResponseEntity<APIResponse> getAllPerformanceEvaluations(HttpServletRequest request){
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        APIResponse apiResponse = rolePerformanceEvaluationService.getAllRolePerformanceEvaluation(Long.valueOf(userId));
        return ResponseEntity.ok(
                APIResponse.builder()
                        .statusCode(apiResponse.getStatusCode())
                        .data(apiResponse.getData())
                        .build()
        );
    }

    @PutMapping(value = "/")
    public ResponseEntity<APIResponse> updatePerformanceEvaluation(@Valid @RequestBody UpdateRolePerformanceRequest updateRolePerformanceRequest,HttpServletRequest request){
        RolePerformanceEvaluationVO rolePerformanceEvaluationVO = new RolePerformanceEvaluationVO();
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        rolePerformanceEvaluationVO.setAdminId(Long.valueOf(userId));
        rolePerformanceEvaluationVO.setRolePerformanceEvaluationId(updateRolePerformanceRequest.performanceId());
        rolePerformanceEvaluationVO.setRoleId(updateRolePerformanceRequest.roleId());
        rolePerformanceEvaluationVO.setCriteria(updateRolePerformanceRequest.criteria());
        rolePerformanceEvaluationVO.setDescription(updateRolePerformanceRequest.description());
        APIResponse apiResponse = rolePerformanceEvaluationService.updateRolePerformanceEvaluation(rolePerformanceEvaluationVO);
        return ResponseEntity.ok(
                APIResponse.builder()
                        .statusCode(apiResponse.getStatusCode())
                        .data(apiResponse.getData())
                        .build()
        );
    }

    @DeleteMapping(value = "/{performanceId}")
    public ResponseEntity<APIResponse> deletePerformanceEvaluation(@PathVariable("performanceId") Long performanceId){
        APIResponse apiResponse = rolePerformanceEvaluationService.deleteRolePerformanceEvaluation(performanceId);
        return ResponseEntity.ok(
                APIResponse.builder()
                        .statusCode(apiResponse.getStatusCode())
                        .data(apiResponse.getData())
                        .build()
        );
    }


}
