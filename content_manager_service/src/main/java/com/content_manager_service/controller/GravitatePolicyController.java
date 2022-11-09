package com.content_manager_service.controller;

import com.content_manager_service.form.CreatePolicyRequest;
import com.content_manager_service.form.UpdatePolicyRequest;
import com.content_manager_service.service.GravitatePolicyService;
import com.model.PolicyVO;
import com.util.APIResponse;
import com.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping(value = "api/v1/content/policy",produces = "application/json")
@RequiredArgsConstructor
public class GravitatePolicyController {

    private final GravitatePolicyService gravitatePolicyService;

    @PostMapping(value = "/create")
    public ResponseEntity<APIResponse> createGravitatePolicy(@Valid @RequestBody CreatePolicyRequest createPolicyRequest,HttpServletRequest request){
        PolicyVO policyVO = new PolicyVO();
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        policyVO.setAdminId(Long.valueOf(userId));
        policyVO.setPolicyName(createPolicyRequest.policyName());
        policyVO.setPolicyType(createPolicyRequest.policyType());
        policyVO.setPolicyFilePath(createPolicyRequest.filePath());
        APIResponse apiResponse = gravitatePolicyService.createGravitatePolicy(policyVO);
        return ResponseEntity.ok(
                APIResponse.builder()
                        .statusCode(apiResponse.getStatusCode())
                        .data(apiResponse.getData())
                        .build()
        );
    }

    @GetMapping(value = "/all")
    public ResponseEntity<APIResponse> getAllPolicies(HttpServletRequest request){
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        APIResponse apiResponse = gravitatePolicyService.getGravitatePolicy(Long.valueOf(userId));
        return ResponseEntity.ok(
                APIResponse.builder()
                        .statusCode(apiResponse.getStatusCode())
                        .data(apiResponse.getData())
                        .build()
        );
    }

    @PutMapping(value = "/")
    public ResponseEntity<APIResponse> updatePolicy(@Valid @RequestBody UpdatePolicyRequest updatePolicyRequest,HttpServletRequest request){
        PolicyVO policyVO = new PolicyVO();
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        policyVO.setAdminId(Long.valueOf(userId));
        policyVO.setPolicyId(updatePolicyRequest.policyId());
        policyVO.setPolicyType(updatePolicyRequest.policyType());
        policyVO.setPolicyName(updatePolicyRequest.policyName());
        policyVO.setPolicyFilePath(updatePolicyRequest.filePath());
        APIResponse apiResponse = gravitatePolicyService.updateGravitatePolicy(policyVO);
        return ResponseEntity.ok(
                APIResponse.builder()
                        .statusCode(apiResponse.getStatusCode())
                        .data(apiResponse.getData())
                        .build()
        );
    }
    @DeleteMapping(value = "/{policyId}")
    public ResponseEntity<APIResponse> deletePolicy(@PathVariable("policyId") Long policyId){
        APIResponse apiResponse = gravitatePolicyService.deleteGravitatePolicy(policyId);
        return ResponseEntity.ok(
                APIResponse.builder()
                        .statusCode(apiResponse.getStatusCode())
                        .data(apiResponse.getData())
                        .build()
        );
    }



}
