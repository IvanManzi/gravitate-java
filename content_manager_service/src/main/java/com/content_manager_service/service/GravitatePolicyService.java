package com.content_manager_service.service;

import com.model.PolicyVO;
import com.util.APIResponse;

public interface GravitatePolicyService {

    APIResponse createGravitatePolicy(PolicyVO policyVO);

    APIResponse getGravitatePolicy(Long userId);

    APIResponse updateGravitatePolicy(PolicyVO policyVO);

    APIResponse deleteGravitatePolicy(Long policyId);
}
