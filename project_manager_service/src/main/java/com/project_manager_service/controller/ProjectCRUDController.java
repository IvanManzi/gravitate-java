package com.project_manager_service.controller;

import com.model.ProjectVO;
import com.project_manager_service.form.CreateProjectRequest;
import com.project_manager_service.form.UpdateProjectRequest;
import com.project_manager_service.service.ProjectCRUDService;
import com.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/project")
@RequiredArgsConstructor
public class ProjectCRUDController {

    private final ProjectCRUDService projectCRUDService;

    @PostMapping(value = "/create")
    public ResponseEntity createProject(@Valid @RequestBody CreateProjectRequest createProjectRequest, HttpServletRequest request) throws IOException {
        ProjectVO projectVO = new ProjectVO();
        String token = request.getHeader(HttpHeaders.AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        projectVO.setAdminId(Long.valueOf(userId));
        projectVO.setProjectLead(createProjectRequest.projectLead());
        projectVO.setJiraId(createProjectRequest.jiraId());
        projectVO.setProjectName(createProjectRequest.projectName());
        projectVO.setClientName(createProjectRequest.clientName());
        projectVO.setClientEmail(createProjectRequest.email());
        projectVO.setPhoneNumber(createProjectRequest.phoneNumber());
        projectVO.setProjectDescription(createProjectRequest.description());
        return projectCRUDService.createProject(projectVO);
    }

    @GetMapping(value = "/all")
    public ResponseEntity getAllProjects() {
        return projectCRUDService.getAllProjects();
    }

    @PutMapping(value = "/")
    public ResponseEntity updateProject(@Valid @RequestBody UpdateProjectRequest updateProjectRequest, HttpServletRequest request) throws IOException {
        ProjectVO projectVO = new ProjectVO();
        String token = request.getHeader(HttpHeaders.AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        projectVO.setProjectId(updateProjectRequest.projectId());
        projectVO.setProjectLead(updateProjectRequest.projectLead());
        projectVO.setJiraId(updateProjectRequest.jiraId());
        projectVO.setStatus(updateProjectRequest.status());
        projectVO.setAdminId(Long.valueOf(userId));
        projectVO.setProjectName(updateProjectRequest.projectName());
        projectVO.setClientName(updateProjectRequest.clientName());
        projectVO.setClientEmail(updateProjectRequest.email());
        projectVO.setPhoneNumber(updateProjectRequest.phoneNumber());
        projectVO.setProjectDescription(updateProjectRequest.description());
        return projectCRUDService.updateProject(projectVO);
    }
    @DeleteMapping(value = "/{projectId}")
    public ResponseEntity deleteProject(@PathVariable("projectId") Long projectId){
        return projectCRUDService.deleteProject(projectId);
    }




}
