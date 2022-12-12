package com.user_manager_service.service.impl;

import com.model.AdditionalPointVO;
import com.user_manager_service.dao.AdditionalPointDao;
import com.user_manager_service.service.AdditionalPointsManagerService;
import com.util.APIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdditionalPointsManagerServiceImpl implements AdditionalPointsManagerService {

    private final AdditionalPointDao additionalPointDao;

    @Override
    public ResponseEntity createAdditionalPoint(AdditionalPointVO additionalPointVO) {
        int result = additionalPointDao.createAdditionalPoint(additionalPointVO);
        if(result > 0){
            return APIResponse.resultSuccess();
        }
        return APIResponse.resultFail();
    }

    @Override
    public ResponseEntity getUserAdditionalPoints(Long userId, String quarter, Integer year) {
        List<Map> userAdditionalPoints = additionalPointDao.getUserAdditionalPoints(userId, quarter, year);
        if(userAdditionalPoints.isEmpty()){
            return APIResponse.resourceNotFound();
        }
        Map<String,Object> data = new HashMap<>();
        data.put("USER_ADDITIONAL_POINTS",userAdditionalPoints);
        return APIResponse.resultSuccess(data);
    }
}
