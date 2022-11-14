package com.content_manager_service.service;

import com.model.ProjectVO;
import com.util.APIResponse;
import org.springframework.http.ResponseEntity;

public interface ProjectManagerService {

    ResponseEntity<APIResponse> createProject(ProjectVO projectVO);

    ResponseEntity<APIResponse> getAllProjectsByAdminId(Long adminId);

    ResponseEntity<APIResponse> updateProject(ProjectVO projectVO);

    ResponseEntity<APIResponse> deleteProject(Long projectId);
}
