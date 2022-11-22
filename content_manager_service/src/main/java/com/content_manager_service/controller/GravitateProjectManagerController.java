package com.content_manager_service.controller;

import com.content_manager_service.form.CreateProjectRequest;
import com.content_manager_service.form.UpdateProjectRequest;
import com.content_manager_service.service.ProjectManagerService;
import com.model.ProjectVO;
import com.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/api/v1/content/project")
@RequiredArgsConstructor
public class GravitateProjectManagerController {

    private final ProjectManagerService projectManagerService;

    @PostMapping(value = "/create")
    public ResponseEntity createProject(@Valid @RequestBody CreateProjectRequest createProjectRequest, HttpServletRequest request) throws IOException {
        ProjectVO projectVO = new ProjectVO();
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        projectVO.setAdminId(Long.valueOf(userId));
        projectVO.setProjectName(createProjectRequest.projectName());
        projectVO.setClientName(createProjectRequest.clientName());
        projectVO.setClientEmail(createProjectRequest.email());
        projectVO.setPhoneNumber(createProjectRequest.phoneNumber());
        projectVO.setProjectDescription(createProjectRequest.description());
        return projectManagerService.createProject(projectVO);
    }

    @GetMapping(value = "/all")
    public ResponseEntity getAllProjects(HttpServletRequest request) throws IOException {
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        return projectManagerService.getAllProjectsByAdminId(Long.valueOf(userId));
    }

    @PutMapping(value = "/")
    public ResponseEntity updateProject(@Valid @RequestBody UpdateProjectRequest updateProjectRequest, HttpServletRequest request) throws IOException {
        ProjectVO projectVO = new ProjectVO();
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        projectVO.setProjectId(updateProjectRequest.projectId());
        projectVO.setStatus(updateProjectRequest.status());
        projectVO.setAdminId(Long.valueOf(userId));
        projectVO.setProjectName(updateProjectRequest.projectName());
        projectVO.setClientName(updateProjectRequest.clientName());
        projectVO.setClientEmail(updateProjectRequest.email());
        projectVO.setPhoneNumber(updateProjectRequest.phoneNumber());
        projectVO.setProjectDescription(updateProjectRequest.description());
        return projectManagerService.updateProject(projectVO);
    }
    @DeleteMapping(value = "/{projectId}")
    public ResponseEntity deleteProject(@PathVariable("projectId") Long projectId){
        return projectManagerService.deleteProject(projectId);
    }




}
