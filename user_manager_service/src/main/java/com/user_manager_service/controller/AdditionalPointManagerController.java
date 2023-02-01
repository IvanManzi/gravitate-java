package com.user_manager_service.controller;

import com.model.AdditionalPointVO;
import com.user_manager_service.RequestMapper.CreateAdditionalPointRequestMapper;
import com.user_manager_service.form.CreateAdditionalPointRequest;
import com.user_manager_service.service.AdditionalPointsManagerService;
import com.util.JwtUtils;
import com.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping(value = "/api/v1/user/additional-point",produces = "application/json")
@RequiredArgsConstructor
public class AdditionalPointManagerController {

    private final AdditionalPointsManagerService additionalPointsManagerService;

    @PostMapping(value = "/create")
    public ResponseEntity createAdditionalPoint(@Valid @RequestBody CreateAdditionalPointRequest createAdditionalPointRequest,
                                                HttpServletRequest request) throws IOException {
        AdditionalPointVO additionalPointVO = CreateAdditionalPointRequestMapper.INSTANCE.map(createAdditionalPointRequest);
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String adminId = JwtUtils.getUserIdFromJwtToken(token);
        additionalPointVO.setAdminId(Long.valueOf(adminId));
        return additionalPointsManagerService.createAdditionalPoint(additionalPointVO);
    }

    @GetMapping(value = "/")
    public ResponseEntity getGravitateUserAdditionalPoints(@RequestParam(value = "quarter") String quarter,
                                                           @RequestParam(value = "year")Integer year,
                                                           HttpServletRequest request) throws IOException {
        List<Integer> quarterValues = new ArrayList<>();
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        if(!ValidationUtil.isNullObject(quarter)){
            quarterValues = new ArrayList<>();
            for(String s : quarter.split(",")) {
                quarterValues.add(Integer.parseInt(s.trim()));
            }
        }
        return additionalPointsManagerService.getUserAdditionalPoints(Long.valueOf(userId), quarterValues, year);
    }

}
