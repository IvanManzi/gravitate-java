package com.content_manager_service.service.impl;


import com.content_manager_service.dao.PositionDao;
import com.content_manager_service.dao.PositionReferralDao;
import com.content_manager_service.dao.PositionSelfReferralDao;
import com.content_manager_service.service.PositionManagerService;
import com.model.PositionReferralVO;
import com.model.PositionSelfReferralVO;
import com.model.PositionVO;
import com.util.APIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PositionManagerServiceImpl implements PositionManagerService {


    private final PositionReferralDao positionReferralDao;

    private final PositionDao positionDao;

    private final PositionSelfReferralDao positionSelfReferralDao;


    @Override
    public ResponseEntity<APIResponse> createPosition(PositionVO positionVO) {
        int result = positionDao.savePosition(positionVO);
        if (result > 0){
            return APIResponse.resultSuccess("Position successfully created .");
        }
        return APIResponse.resultFail();
    }

    @Override
    public ResponseEntity<APIResponse> getAllPositions(String opportunityType) {
        List<Map> positions = positionDao.getAllPositions(opportunityType);
        if(positions.isEmpty()){
            return APIResponse.resourceNotFound();
        }
        Map<String,Object> data = new HashMap<>();
        data.put("POSITIONS",positions);
        return APIResponse.resultSuccess(data);
    }

    @Override
    public ResponseEntity<APIResponse> updatePosition(PositionVO positionVO) {
        int result = positionDao.updatePosition(positionVO);
        if(result > 0){
            return APIResponse.resultSuccess("Position successfully updated. ");
        }
        return APIResponse.resultFail();
    }

    @Override
    public ResponseEntity<APIResponse> createPositionReferral(PositionReferralVO positionReferralVO) {
        int result = positionReferralDao.savePositionReferral(positionReferralVO);
        if(result > 0){
            return APIResponse.resultSuccess("Position referral successfully saved. ");
        }
        return APIResponse.resultFail();
    }

    @Override
    public ResponseEntity<APIResponse> getReferredPositions(Long userId, String userLevel) {
        List<Map> referredPositions = positionReferralDao.getAllReferredPositions(userId,userLevel);
        if(referredPositions.isEmpty()){
            return APIResponse.resourceNotFound();
        }
        Map<String, Object> data = new HashMap<>();
        data.put("REFERRED_POSITIONS", referredPositions);
        return APIResponse.resultSuccess(data);
    }

    @Override
    public ResponseEntity<APIResponse> createSelfReferral(PositionSelfReferralVO positionSelfReferralVO) {
        int result = positionSelfReferralDao.saveSelfReferral(positionSelfReferralVO);
        if(result > 0){
            return APIResponse.resultSuccess("Position self referral created.");
        }
        return APIResponse.resultFail();
    }

    @Override
    public ResponseEntity<APIResponse> getSelfReferredPositions(Long userId, String userLevel) {
        List<Map> selfReferredPositions = positionSelfReferralDao.getSelfReferredPositions(userId,userLevel);
        if(selfReferredPositions.isEmpty()){
            return APIResponse.resourceNotFound();
        }
        Map<String,Object> data = new HashMap<>();
        data.put("SELF_REFERRED_POSITIONS",selfReferredPositions);
        return APIResponse.resultSuccess(data);
    }

    @Override
    public ResponseEntity<APIResponse> updateSelfReferredPositionStatus(PositionSelfReferralVO positionSelfReferralVO) {
        int result = positionSelfReferralDao.updateSelfReferredPositionStatus(positionSelfReferralVO);
        if(result > 0){
            return APIResponse.resultSuccess("Status successfully updated. ");
        }
        return APIResponse.resultFail();
    }

    @Override
    public ResponseEntity<APIResponse> deletePosition(Long positionId) {
        int result = positionDao.deletePosition(positionId);
        if(result > 0){
            return APIResponse.resultSuccess("Position successfully deleted.");
        }
        return APIResponse.resultFail();
    }

    @Override
    public ResponseEntity<APIResponse> updatePositionReferralStatus(PositionReferralVO positionReferralVO) {
        int status = positionReferralDao.updateReferredPosition(positionReferralVO);
        if(status > 0){
            return APIResponse.resultSuccess("Referred position status successfully updated. ");
        }
        return APIResponse.resultFail();
    }
}
