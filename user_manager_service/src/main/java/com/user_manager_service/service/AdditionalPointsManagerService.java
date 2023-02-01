package com.user_manager_service.service;


import com.model.AdditionalPointVO;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

public interface AdditionalPointsManagerService {
    ResponseEntity createAdditionalPoint(AdditionalPointVO additionalPointVO);

    ResponseEntity getUserAdditionalPoints(Long userId, List<Integer> quarters, Integer year);
}
