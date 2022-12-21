package com.content_manager_service.dao;

import com.model.ClientReferralVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ClientReferralDao {

    int saveClientReferral(ClientReferralVO clientReferralVO);

    List<Map> getAllClientReferrals();

    int updateClientReferralStatus(ClientReferralVO clientReferralVO);
}
