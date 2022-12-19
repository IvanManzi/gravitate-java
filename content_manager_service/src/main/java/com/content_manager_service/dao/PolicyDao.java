package com.content_manager_service.dao;

import com.model.PolicyVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PolicyDao {

    int createGravitatePolicy(PolicyVO policyVO);
    List<PolicyVO> getAllPolicies(String policyType);
    int updateGravitatePolicy(PolicyVO policyVO);
    int deleteGravitatePolicy(Long policyId);
}
