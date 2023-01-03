package com.project_manager_service.service;

import com.model.ProjectVO;
import com.util.APIResponse;
import org.springframework.http.ResponseEntity;

public interface ProjectManagerService {

    ResponseEntity<APIResponse> createProject(ProjectVO projectVO);

    ResponseEntity<APIResponse> getAllProjects(Long userId, String role,Integer phase);

    ResponseEntity<APIResponse> updateProject(ProjectVO projectVO);

    ResponseEntity<APIResponse> markProjectAsFavorite(Long projectId);

    ResponseEntity<APIResponse> updateProjectPhase(ProjectVO projectVO);

    ResponseEntity<APIResponse> deleteProject(Long projectId);
}
