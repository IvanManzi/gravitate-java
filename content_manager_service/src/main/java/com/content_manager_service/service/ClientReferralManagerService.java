package com.content_manager_service.service;

import com.model.ClientReferralVO;
import com.util.APIResponse;
import org.springframework.http.ResponseEntity;

public interface ClientReferralManagerService {


    ResponseEntity<APIResponse> createClientReferral(ClientReferralVO clientReferralVO);


    ResponseEntity<APIResponse> getAllClientReferrals();


    ResponseEntity<APIResponse> updateClientReferralStatus(ClientReferralVO clientReferralVO);





}
