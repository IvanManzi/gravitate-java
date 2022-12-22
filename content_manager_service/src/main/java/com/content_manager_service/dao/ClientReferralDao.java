package com.content_manager_service.dao;

import com.model.ClientReferralVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ClientReferralDao {

    int saveClientReferral(ClientReferralVO clientReferralVO);

    List<Map> getAllClientReferrals(@Param("userId")Long userId,@Param("role")String role);

    int updateClientReferralStatus(ClientReferralVO clientReferralVO);
}
