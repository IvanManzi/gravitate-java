package com.content_manager_service.service.impl;


import com.content_manager_service.dao.PolicyDao;
import com.content_manager_service.service.GravitatePolicyService;
import com.model.PolicyVO;
import com.util.APIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity createGravitatePolicy(PolicyVO policyVO) {
        int result = policyDao.createGravitatePolicy(policyVO);
        if(result > 0){
            return APIResponse.resultSuccess("Policy successfully created.");
        }else{
            return APIResponse.resultFail();
        }
    }

    @Override
    public ResponseEntity getGravitatePolicy(String policyType) {
        List<PolicyVO> policies = policyDao.getAllPolicies(policyType);
        if(policies.isEmpty()){
            return APIResponse.resourceNotFound();
        }else{
            Map<String,Object> data = new HashMap<>();
            data.put("POLICIES",policies);
            return APIResponse.resultSuccess(data);
        }
    }

    @Override
    public ResponseEntity updateGravitatePolicy(PolicyVO policyVO) {
        int result = policyDao.updateGravitatePolicy(policyVO);
        if(result > 0){
            return APIResponse.resultSuccess("Policy updated successfully.");
        }else{
            return APIResponse.resultFail();
        }
    }

    @Override
    public ResponseEntity deleteGravitatePolicy(Long policyId) {
        int result = policyDao.deleteGravitatePolicy(policyId);
        if(result > 0){
            return APIResponse.resultSuccess("Policy successfully deleted. ");
        }else{
            return APIResponse.resultFail();
        }
    }

}
