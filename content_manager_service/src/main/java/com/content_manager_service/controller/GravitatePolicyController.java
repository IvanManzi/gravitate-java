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

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping(value = "api/v1/content/policy",produces = "application/json")
@RequiredArgsConstructor
public class GravitatePolicyController {

    private final GravitatePolicyService gravitatePolicyService;

    @PostMapping(value = "/create")
    public ResponseEntity createGravitatePolicy(@Valid @RequestBody CreatePolicyRequest createPolicyRequest,HttpServletRequest request) throws IOException {
        PolicyVO policyVO = new PolicyVO();
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        policyVO.setAdminId(Long.valueOf(userId));
        policyVO.setPolicyName(createPolicyRequest.policyName());
        policyVO.setPolicyType(createPolicyRequest.policyType());
        policyVO.setPolicyFilePath(createPolicyRequest.filePath());
        return gravitatePolicyService.createGravitatePolicy(policyVO);
    }

    @GetMapping(value = "/all")
    public ResponseEntity getAllPolicies(@RequestParam(value = "type",required = false) String type) throws IOException {
        return gravitatePolicyService.getGravitatePolicy(type);
    }

    @PutMapping(value = "/")
    public ResponseEntity updatePolicy(@Valid @RequestBody UpdatePolicyRequest updatePolicyRequest,HttpServletRequest request) throws IOException {
        PolicyVO policyVO = new PolicyVO();
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        policyVO.setAdminId(Long.valueOf(userId));
        policyVO.setPolicyId(updatePolicyRequest.policyId());
        policyVO.setPolicyType(updatePolicyRequest.policyType());
        policyVO.setPolicyName(updatePolicyRequest.policyName());
        policyVO.setPolicyFilePath(updatePolicyRequest.filePath());
        return gravitatePolicyService.updateGravitatePolicy(policyVO);
    }
    @DeleteMapping(value = "/{policyId}")
    public ResponseEntity deletePolicy(@PathVariable("policyId") Long policyId){
        return gravitatePolicyService.deleteGravitatePolicy(policyId);
    }



}
