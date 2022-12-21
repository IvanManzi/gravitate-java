package com.content_manager_service.service.impl;

import com.content_manager_service.dao.ClientReferralDao;
import com.content_manager_service.service.ClientReferralManagerService;
import com.model.ClientReferralVO;
import com.util.APIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class ClientReferralManagerServiceImpl implements ClientReferralManagerService {


    private final ClientReferralDao clientReferralDao;



    @Override
    public ResponseEntity<APIResponse> createClientReferral(ClientReferralVO clientReferralVO) {
        int status = clientReferralDao.saveClientReferral(clientReferralVO);
        if(status > 0){
            return APIResponse.resultSuccess("Client referral successfully saved. ");
        }
        return APIResponse.resultFail();
    }

    @Override
    public ResponseEntity<APIResponse> getAllClientReferrals() {
        List<Map> clientReferrals = clientReferralDao.getAllClientReferrals();
        if(clientReferrals.isEmpty()){
            return APIResponse.resourceNotFound();
        }
        Map<String,Object> data = new HashMap<>();
        data.put("CLIENT_REFERRALS",clientReferrals);
        return APIResponse.resultSuccess(data);
    }

    @Override
    public ResponseEntity<APIResponse> updateClientReferralStatus(ClientReferralVO clientReferralVO) {
        int status = clientReferralDao.updateClientReferralStatus(clientReferralVO);
        if(status > 1){
            return APIResponse.resultSuccess();
        }
        return APIResponse.resultFail();
    }
}
