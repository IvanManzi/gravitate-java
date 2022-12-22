package com.content_manager_service.controller;


import com.content_manager_service.form.CreateClientReferralRequest;
import com.content_manager_service.service.ClientReferralManagerService;
import com.model.ClientReferralVO;
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
@RequestMapping(value = "/api/v1/content/client-referral")
@RequiredArgsConstructor
public class ClientReferralManagerController {

    private final ClientReferralManagerService clientReferralManagerService;


    @PostMapping(value = "/create")
    public ResponseEntity<APIResponse> createClientReferral(@Valid @RequestBody CreateClientReferralRequest createClientReferralRequest,
                                                            HttpServletRequest request) throws IOException {
        ClientReferralVO clientReferralVO = new ClientReferralVO();
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);

        clientReferralVO.setReferredBy(Long.valueOf(userId));
        clientReferralVO.setIsReferencable(createClientReferralRequest.referencable());
        clientReferralVO.setClientEmail(createClientReferralRequest.email());
        clientReferralVO.setClientName(createClientReferralRequest.clientName());
        clientReferralVO.setClientDescription(createClientReferralRequest.clientDescription());
        clientReferralVO.setOrganisationName(createClientReferralRequest.organisationName());
        clientReferralVO.setProjectDetails(createClientReferralRequest.projectDescription());
        return clientReferralManagerService.createClientReferral(clientReferralVO);
    }


    @GetMapping(value = "/all")
    public ResponseEntity<APIResponse> getAllClientReferrals(HttpServletRequest request) throws IOException {
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        String role = JwtUtils.getUserRoleFromJwtToken(token);
        return clientReferralManagerService.getAllClientReferrals(Long.valueOf(userId),role);
    }


    @PutMapping(value = "/{clientReferralId}/status/{status}")
    public ResponseEntity<APIResponse> updateClientReferralStatus(@PathVariable("referralId")Long referralId,
                                                                  @PathVariable("status") Integer status){
        ClientReferralVO clientReferralVO = new ClientReferralVO();
        clientReferralVO.setClientReferralId(referralId);
        clientReferralVO.setReferralStatus(status);
        return clientReferralManagerService.updateClientReferralStatus(clientReferralVO);
    }







}
