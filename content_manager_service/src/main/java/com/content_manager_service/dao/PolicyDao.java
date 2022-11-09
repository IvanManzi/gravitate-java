package com.content_manager_service.dao;

import com.model.PolicyVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PolicyDao {

    int createGravitatePolicy(PolicyVO policyVO);
    List<PolicyVO> getAllPolicies(Long adminId);
    int updateGravitatePolicy(PolicyVO policyVO);
    int deleteGravitatePolicy(Long policyId);
}
