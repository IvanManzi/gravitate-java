package com.content_manager_service.controller;


import com.content_manager_service.form.CreateProjectIncentiveRequest;
import com.content_manager_service.service.ProjectIncentiveManagerService;
import com.model.ProjectIncentiveVO;
import com.util.APIResponse;
import com.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping(value = "api/v1/content/project-incentive",produces = "application/json")
@RequiredArgsConstructor
public class ProjectIncentiveManagerController {

    private final ProjectIncentiveManagerService projectIncentiveManagerService;


    @PostMapping(value = "/create")
    public ResponseEntity<APIResponse> createProjectIncentive(@Valid @RequestBody CreateProjectIncentiveRequest createProjectIncentiveRequest,
                                                              HttpServletRequest request) throws IOException {
        ProjectIncentiveVO projectIncentiveVO = new ProjectIncentiveVO();
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String adminId = JwtUtils.getUserIdFromJwtToken(token);

        projectIncentiveVO.setAdminId(Long.valueOf(adminId));
        projectIncentiveVO.setUserId(createProjectIncentiveRequest.userId());
        projectIncentiveVO.setProjectId(createProjectIncentiveRequest.projectId());
        projectIncentiveVO.setTotalHours(createProjectIncentiveRequest.totalHours());
        projectIncentiveVO.setPerformanceBonus(createProjectIncentiveRequest.performanceBonus());
        projectIncentiveVO.setEmployeeReferral(createProjectIncentiveRequest.employeeReferral());
        projectIncentiveVO.setClientReferral(createProjectIncentiveRequest.clientReferral());
        projectIncentiveVO.setHotOpportunity(createProjectIncentiveRequest.hotOpportunity());
        return projectIncentiveManagerService.createProjectIncentive(projectIncentiveVO);
    }


}
