package com.project_manager_service.service;

import com.model.ProjectVO;
import com.util.APIResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProjectCRUDService {

    ResponseEntity<APIResponse> createProject(ProjectVO projectVO);

    ResponseEntity<APIResponse> getAllProjects(Long userId, String role);

    ResponseEntity<APIResponse> updateProject(ProjectVO projectVO);

    ResponseEntity<APIResponse> deleteProject(Long projectId);
}
