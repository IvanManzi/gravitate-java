package com.content_manager_service.service;

import com.model.ProjectVO;
import com.util.APIResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProjectManagerService {

    ResponseEntity<APIResponse> createProject(ProjectVO projectVO);

    ResponseEntity<APIResponse> getAllProjectsByAdminId(Long adminId);

    ResponseEntity<APIResponse> updateProject(ProjectVO projectVO);

    boolean assignUserToProject(Long userId, List<Long> projects);
    ResponseEntity getUsersAssignedToProjects();
    ResponseEntity getGravitateUserProjects(Long userId);
    ResponseEntity<APIResponse> deleteProject(Long projectId);
}
