package com.content_manager_service.service;

import com.model.PositionReferralVO;
import com.model.PositionVO;
import com.util.APIResponse;
import org.springframework.http.ResponseEntity;

public interface PositionManagerService {

    ResponseEntity<APIResponse> createPosition(PositionVO positionVO);


    ResponseEntity<APIResponse> getAllPositions(String opportunityType);


    ResponseEntity<APIResponse> updatePosition(PositionVO positionVO);


    ResponseEntity<APIResponse> createPositionReferral(PositionReferralVO positionReferralVO);


    ResponseEntity<APIResponse> getReferredPositions(Long userId, String userLevel);


    ResponseEntity<APIResponse> deletePosition(Long positionId);


    ResponseEntity<APIResponse> updatePositionReferralStatus(PositionReferralVO positionReferralVO);
}
