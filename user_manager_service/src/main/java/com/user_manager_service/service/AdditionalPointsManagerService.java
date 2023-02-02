package com.user_manager_service.service;


import com.model.AdditionalPointVO;
import com.user_manager_service.form.CreateAdditionalPointRequest;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface AdditionalPointsManagerService {
    ResponseEntity createAdditionalPoint(CreateAdditionalPointRequest createAdditionalPointRequest, String token) throws IOException;

    ResponseEntity getUserAdditionalPoints(Long userId, List<Integer> quarters, Integer year);
}
