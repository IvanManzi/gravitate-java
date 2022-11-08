package com.content_manager_service.service.impl;


import com.content_manager_service.dao.PolicyDao;
import com.content_manager_service.service.GravitatePolicyService;
import com.model.PolicyVO;
import com.util.APIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GravitatePolicyServiceImpl implements GravitatePolicyService {

    private final PolicyDao policyDao;


    @Override
    public APIResponse createGravitatePolicy(PolicyVO policyVO) {
        return null;
    }

    @Override
    public APIResponse getGravitatePolicy(Long userId) {
        return null;
    }

    @Override
    public APIResponse updateGravitatePolicy(PolicyVO policyVO) {
        return null;
    }

    @Override
    public APIResponse deleteGravitatePolicy(Long policyId) {
        return null;
    }
}
