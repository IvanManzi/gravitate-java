package com.content_manager_service.service.impl;


import com.content_manager_service.dao.PolicyDao;
import com.content_manager_service.service.GravitatePolicyService;
import com.model.PolicyVO;
import com.util.APIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.util.Constants.*;

@Service
@RequiredArgsConstructor
public class GravitatePolicyServiceImpl implements GravitatePolicyService {

    private final PolicyDao policyDao;


    @Override
    public APIResponse createGravitatePolicy(PolicyVO policyVO) {
        int result = policyDao.createGravitatePolicy(policyVO);
        if(result > 0){
            return new APIResponse(RESOURCE_CREATED,"Policy successfully created.");
        }else{
            return new APIResponse(BAD_REQUEST);
        }
    }

    @Override
    public APIResponse getGravitatePolicy(Long userId) {
        List<PolicyVO> policies = policyDao.getAllPolicies(userId);
        if(policies.isEmpty()){
            return new APIResponse(NOT_FOUND,"No policies found.");
        }else{
            Map<String,Object> data = new HashMap<>();
            data.put("POLICIES",policies);
            return new APIResponse(REQUEST_OK,data);
        }
    }

    @Override
    public APIResponse updateGravitatePolicy(PolicyVO policyVO) {
        int result = policyDao.updateGravitatePolicy(policyVO);
        if(result > 0){
            return new APIResponse(REQUEST_OK,"Policy updated successfully.");
        }else{
            return new APIResponse(BAD_REQUEST);
        }
    }

    @Override
    public APIResponse deleteGravitatePolicy(Long policyId) {
        int result = policyDao.deleteGravitatePolicy(policyId);
        if(result > 0){
            return new APIResponse(REQUEST_OK,"Policy successfully deleted. ");
        }else{
            return new APIResponse(BAD_REQUEST);
        }
    }
}
