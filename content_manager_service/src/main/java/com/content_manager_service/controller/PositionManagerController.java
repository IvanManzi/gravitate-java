package com.content_manager_service.controller;


import com.content_manager_service.form.CreatePositionReferralRequest;
import com.content_manager_service.form.CreatePositionRequest;
import com.content_manager_service.form.CreatePositionSelfReferralRequest;
import com.content_manager_service.form.UpdatePositionRequest;
import com.content_manager_service.service.PositionManagerService;
import com.model.PositionReferralVO;
import com.model.PositionSelfReferralVO;
import com.model.PositionVO;
import com.util.APIResponse;
import com.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;


@RestController
@RequestMapping(value = "/api/v1/content/position", produces = "application/json")
@RequiredArgsConstructor
public class PositionManagerController {


    private final PositionManagerService positionManagerService;


    @PostMapping(value = "/create")
    public ResponseEntity<APIResponse> createPosition(@Valid @RequestBody CreatePositionRequest createPositionRequest,
                                                      HttpServletRequest request) throws IOException {
        PositionVO positionVO = new PositionVO();
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);

        positionVO.setAdminId(Long.valueOf(userId));
        positionVO.setJobId(createPositionRequest.jobId());
        positionVO.setExperience(createPositionRequest.positionExperience());
        positionVO.setKeySkills(createPositionRequest.skills());
        positionVO.setRoleId(createPositionRequest.roleId());
        positionVO.setKra(createPositionRequest.positionKra());
        positionVO.setPositionType(createPositionRequest.positionType());
        positionVO.setOpportunityType(createPositionRequest.opportunityType());
        positionVO.setReferralAmount(createPositionRequest.referralAmount());
        positionVO.setStartDate(createPositionRequest.beginDate());
        positionVO.setPoints(createPositionRequest.points());
        positionVO.setEndDate(createPositionRequest.endDate());
        positionVO.setIncentiveAmount(createPositionRequest.incentiveAmount());
        positionVO.setProjectId(createPositionRequest.projectId());

        return positionManagerService.createPosition(positionVO);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<APIResponse> getAllPositions(@RequestParam(value = "type",required = false) String positionType) {
        return positionManagerService.getAllPositions(positionType);
    }


    @PutMapping(value = "/")

    public ResponseEntity<APIResponse> updatePosition(@Valid @RequestBody UpdatePositionRequest updatePositionRequest){
        PositionVO positionVO = new PositionVO();
        positionVO.setPositionId(updatePositionRequest.positionId());
        positionVO.setJobId(updatePositionRequest.jobId());
        positionVO.setExperience(updatePositionRequest.positionExperience());
        positionVO.setKeySkills(updatePositionRequest.skills());
        positionVO.setRoleId(updatePositionRequest.roleId());
        positionVO.setKra(updatePositionRequest.positionKra());
        positionVO.setPositionType(updatePositionRequest.positionType());
        positionVO.setOpportunityType(updatePositionRequest.opportunityType());
        positionVO.setReferralAmount(updatePositionRequest.referralAmount());
        positionVO.setStartDate(updatePositionRequest.beginDate());
        positionVO.setEndDate(updatePositionRequest.endDate());
        positionVO.setIncentiveAmount(updatePositionRequest.incentiveAmount());
        positionVO.setProjectId(updatePositionRequest.projectId());

        return positionManagerService.updatePosition(positionVO);
    }


    @PostMapping(value = "/referral/create")
    public ResponseEntity<APIResponse> createPositionReferral(@Valid @RequestBody CreatePositionReferralRequest createPositionReferralRequest, HttpServletRequest request) throws IOException {
        PositionReferralVO positionReferralVO = new PositionReferralVO();

        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);

        positionReferralVO.setEmailId(createPositionReferralRequest.email());
        positionReferralVO.setFirstName(createPositionReferralRequest.firstName());
        positionReferralVO.setLastName(createPositionReferralRequest.lastName());
        positionReferralVO.setPhoneNumber(createPositionReferralRequest.phoneNumber());
        positionReferralVO.setKeySkills(createPositionReferralRequest.keySkills());
        positionReferralVO.setCountry(createPositionReferralRequest.country());
        positionReferralVO.setCvPath(createPositionReferralRequest.cvPath());
        positionReferralVO.setPositionId(createPositionReferralRequest.positionId());
        positionReferralVO.setReferredBy(Long.valueOf(userId));


        return positionManagerService.createPositionReferral(positionReferralVO);
    }


    @GetMapping(value = "/referral/all")
    public ResponseEntity<APIResponse> getAllPositionReferrals(HttpServletRequest request) throws IOException {
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        String role = JwtUtils.getUserRoleFromJwtToken(token);
        return positionManagerService.getReferredPositions(Long.valueOf(userId),role);

    }

    @PostMapping(value = "/self/referral/create")
    public ResponseEntity<APIResponse> createPositionSelfReferral(@Valid @RequestBody CreatePositionSelfReferralRequest createPositionSelfReferralRequest, HttpServletRequest request) throws IOException {
        PositionSelfReferralVO positionSelfReferralVO = new PositionSelfReferralVO();

        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);

        positionSelfReferralVO.setUserId(Long.valueOf(userId));
        positionSelfReferralVO.setPositionId(createPositionSelfReferralRequest.positionId());

        return positionManagerService.createSelfReferral(positionSelfReferralVO);
    }


    @GetMapping(value = "/self/referral/all")
    public ResponseEntity<APIResponse> getAllSelfReferredPositions(HttpServletRequest request) throws IOException {
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        String role = JwtUtils.getUserRoleFromJwtToken(token);
        return positionManagerService.getSelfReferredPositions(Long.valueOf(userId),role);

    }

    @PutMapping(value = "/self/referral/{referralId}/status/{status}")
    public ResponseEntity<APIResponse> updateSelfReferredPositionStatus(@PathVariable("referralId")Long referralId,
                                                                    @PathVariable("status") Integer status){
        PositionSelfReferralVO positionSelfReferralVO = new PositionSelfReferralVO();
        positionSelfReferralVO.setPositionSelfReferralId(referralId);
        positionSelfReferralVO.setReferralStatus(status);
        return positionManagerService.updateSelfReferredPositionStatus(positionSelfReferralVO);
    }


    @DeleteMapping(value = "/{positionId}")
    public ResponseEntity<APIResponse> deletePosition(@PathVariable("positionId") Long positionId){
        return positionManagerService.deletePosition(positionId);
    }



    @PutMapping(value = "/referral/{referralId}/status/{status}")
    public ResponseEntity<APIResponse> updatePositionReferralStatus(@PathVariable("referralId")Long referralId,
                                                                    @PathVariable("status") Integer status){
        PositionReferralVO positionReferralVO = new PositionReferralVO();
        positionReferralVO.setPositionReferralId(referralId);
        positionReferralVO.setReferralStatus(status);
        return positionManagerService.updatePositionReferralStatus(positionReferralVO);
    }


}
