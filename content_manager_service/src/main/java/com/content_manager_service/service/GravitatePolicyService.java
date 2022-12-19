package com.content_manager_service.service;

import com.model.PolicyVO;
import com.util.APIResponse;
import org.springframework.http.ResponseEntity;

public interface GravitatePolicyService {

    ResponseEntity createGravitatePolicy(PolicyVO policyVO);

    ResponseEntity getGravitatePolicy(String policyType);

    ResponseEntity updateGravitatePolicy(PolicyVO policyVO);

    ResponseEntity deleteGravitatePolicy(Long policyId);
}
