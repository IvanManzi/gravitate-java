package com.project_manager_service.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.model.ProjectVO;
import com.project_manager_service.form.CreateProjectRequest;
import com.project_manager_service.form.UpdateProjectRequest;
import com.project_manager_service.service.ProjectManagerService;
import com.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/project")
@RequiredArgsConstructor
public class ProjectManagerController {

    private final ProjectManagerService projectManagerService;

    @PostMapping(value = "/create")
    public ResponseEntity createProject(@Valid @RequestBody CreateProjectRequest createProjectRequest, HttpServletRequest request) throws IOException, UnirestException {
        ProjectVO projectVO = new ProjectVO();
        String token = request.getHeader(HttpHeaders.AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        projectVO.setAdminId(Long.valueOf(userId));
        projectVO.setProjectLead(createProjectRequest.projectLead());
        projectVO.setLeadJiraAccountId(createProjectRequest.jiraAccountId());
        projectVO.setProjectName(createProjectRequest.projectName());
        projectVO.setClientName(createProjectRequest.clientName());
        projectVO.setClientEmail(createProjectRequest.email());
        projectVO.setStartDate(createProjectRequest.startDate());
        projectVO.setTechnologies(createProjectRequest.technologies());
        projectVO.setPhoneNumber(createProjectRequest.phoneNumber());
        projectVO.setProjectDescription(createProjectRequest.description());
        return projectManagerService.createProject(projectVO);
    }

    @GetMapping(value = "/all")
    public ResponseEntity getAllProjects(HttpServletRequest request,@RequestParam(value = "phase",required = false)Integer phase) throws IOException {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        String role = JwtUtils.getUserRoleFromJwtToken(token);
        return projectManagerService.getAllProjects(Long.valueOf(userId),role,phase);
    }

    @PutMapping(value = "/")
    public ResponseEntity updateProject(@Valid @RequestBody UpdateProjectRequest updateProjectRequest, HttpServletRequest request) throws IOException, UnirestException {
        ProjectVO projectVO = new ProjectVO();
        String token = request.getHeader(HttpHeaders.AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        projectVO.setProjectId(updateProjectRequest.projectId());
        projectVO.setProjectLead(updateProjectRequest.projectLead());
        projectVO.setLeadJiraAccountId(updateProjectRequest.jiraAccountId());
        projectVO.setJiraProjectKey(updateProjectRequest.jiraProjectKey());
        projectVO.setStatus(updateProjectRequest.status());
        projectVO.setAdminId(Long.valueOf(userId));
        projectVO.setProjectName(updateProjectRequest.projectName());
        projectVO.setClientName(updateProjectRequest.clientName());
        projectVO.setClientEmail(updateProjectRequest.email());
        projectVO.setPhoneNumber(updateProjectRequest.phoneNumber());
        projectVO.setProjectDescription(updateProjectRequest.description());
        projectVO.setStartDate(updateProjectRequest.startDate());
        projectVO.setTechnologies(updateProjectRequest.technologies());
        return projectManagerService.updateProject(projectVO);
    }
    @DeleteMapping(value = "/{jiraProjectKey}")
    public ResponseEntity deleteProject(@PathVariable("jiraProjectKey") String projectId) throws UnirestException, JsonProcessingException {
        return projectManagerService.deleteProject(projectId);
    }

    @PutMapping(value = "/{projectId}/mark-favorite")
    public ResponseEntity markProjectAsFavorite(@PathVariable("projectId") Long projectId){
        return projectManagerService.markProjectAsFavorite(projectId);
    }

    @PutMapping(value = "/{projectId}/phase/{phaseId}")
    public ResponseEntity updateProjectPhase(@PathVariable("projectId") Long projectId,
                                             @PathVariable("phaseId")Integer phase){
        ProjectVO projectVO = new ProjectVO();
        projectVO.setPhase(phase);
        projectVO.setProjectId(projectId);
        return projectManagerService.updateProjectPhase(projectVO);
    }


    @PutMapping(value = "/{projectId}/status/{phaseId}")
    public ResponseEntity updateProjectStatus(@PathVariable("projectId") Long projectId,
                                             @PathVariable("phaseId")Integer status){
        ProjectVO projectVO = new ProjectVO();
        projectVO.setStatus(status);
        projectVO.setProjectId(projectId);
        return projectManagerService.updateProjectStatus(projectVO);
    }





}
